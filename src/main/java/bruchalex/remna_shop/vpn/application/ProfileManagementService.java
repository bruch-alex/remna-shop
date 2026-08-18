package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.ProfileManagementAdapter;
import bruchalex.remna_shop.vpn.application.port.in.ProfileManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.exception.ResourceNotFoundException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileManagementService implements ProfileManagementUseCase {

    private final ProfileManagementAdapter profileManagementAdapter;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final MeterRegistry meterRegistry;
    private final Executor remnawaveApiExecutor;

    @Override
    public ProfileResult createProfile(CreateProfileCommand command) {
        var profileId = UUID.randomUUID();
        Profile remoteProfile = profileManagementAdapter.create(profileId);


        remoteProfile.setNewUserId(command.userId());
        remoteProfile.renameProfile(command.name());

        Profile saved;
        try {
            saved = profileRepository.save(remoteProfile);
        } catch (Exception e) {
            log.error("DB save failed after remote profile {} was created; attempting rollback", remoteProfile.getId(), e);
            var isDeleted = profileManagementAdapter.delete(remoteProfile.getRemnawaveUserUuid());
            if (!isDeleted) {
                log.debug("Remote profile {} was deleted", remoteProfile.getId());
            }
            throw e;
        }

        return profileMapper.toResult(saved);
    }

    @Override
    @Transactional
    public ProfileResult getProfileSummary(GetProfileSummaryCommand command) {
        var profileInDB = profileRepository.findByIdAndUserId(command.profileId(), command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        var remoteProfile = profileManagementAdapter.getProfileByUsername(command.profileId().toString());
        Map<String, Device> remoteDevicesByHwid = profileManagementAdapter
                .getDevicesByExternalId(remoteProfile.getRemnawaveUserUuid()).stream()
                .collect(Collectors.toMap(Device::getId, Function.identity()));

        profileInDB.merge(remoteProfile);
        profileInDB.mergeDevices(remoteDevicesByHwid);
        var savedProfile = profileRepository.save(profileInDB);

        return profileMapper.toResult(savedProfile, remoteDevicesByHwid);
    }

    @Override
    public List<ProfileResult> syncRemoteProfiles(SyncProfileCommand command) {
        List<Profile> remoteProfiles = profileManagementAdapter.getProfilesByEmail(command.email());
        List<ProfileResult> results = new ArrayList<>();

        remoteProfiles.forEach(p -> {
            p.setNewUserId(command.userId());
            Map<String, Device> remoteDevicesByHwid = profileManagementAdapter
                    .getDevicesByExternalId(p.getRemnawaveUserUuid())
                    .stream()
                    .collect(Collectors.toMap(Device::getId, Function.identity()));
            p.mergeDevices(remoteDevicesByHwid);
            var savedProfile = profileRepository.save(p);
            results.add(profileMapper.toResult(savedProfile, remoteDevicesByHwid));
        });
        return results;
    }

    @Override
    public List<ProfileResult> getProfiles(GetProfilesCommand command) {
        Timer.Sample overallSample = Timer.start(meterRegistry);

        var profiles = profileRepository.findAllByUserIdWithDevices(command.userId());
        log.info("Profiles found: {}", profiles.size());

        List<CompletableFuture<ProfileWithDevices>> futures = profiles.stream()
                .map(profile -> CompletableFuture.supplyAsync(() -> fetchDevices(profile), remnawaveApiExecutor)
                        .orTimeout(1, TimeUnit.SECONDS)
                        .exceptionally(ex -> {
                            log.warn("Failed to fetch devices for profile {}", profile.getId(), ex);
                            return new ProfileWithDevices(profile, Map.of());
                        }))
                .toList();

        List<ProfileWithDevices> withDevices = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        var results = withDevices.stream()
                .map(pwd -> {
                    Timer.Sample dbSample = Timer.start(meterRegistry);
                    pwd.profile().mergeDevices(pwd.devices());
                    var savedProfile = profileRepository.save(pwd.profile());
                    dbSample.stop(meterRegistry.timer("profileManagementService.db.save"));

                    return profileMapper.toResult(savedProfile, pwd.devices());
                })
                .toList();

        overallSample.stop(meterRegistry.timer("profileManagementService.getProfiles.total"));
        return results;
    }

    @Override
    public ProfileResult renameProfile(RenameProfileCommand command) {
        var profile = profileRepository.findByIdAndUserId(command.profileId(), command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profile.renameProfile(command.newName());
        var saved = profileRepository.save(profile);
        return profileMapper.toResult(saved);
    }

    private ProfileWithDevices fetchDevices(Profile profile) {
        Timer.Sample apiSample = Timer.start(meterRegistry);
        Map<String, Device> remoteDevicesByHwid = profileManagementAdapter
                .getDevicesByExternalId(profile.getRemnawaveUserUuid())
                .stream()
                .collect(Collectors.toMap(Device::getId, Function.identity()));
        apiSample.stop(meterRegistry.timer("profileManagementService.external.fetch"));
        return new ProfileWithDevices(profile, remoteDevicesByHwid);
    }

    private record ProfileWithDevices(Profile profile, Map<String, Device> devices) {
    }

}
