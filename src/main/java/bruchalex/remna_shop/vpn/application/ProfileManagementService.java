package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.ProfileManagementAdapter;
import bruchalex.remna_shop.vpn.application.port.in.ProfileManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileManagementService implements ProfileManagementUseCase {

    private final ProfileManagementAdapter profileManagementAdapter;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

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
        profileInDB.syncDevices(remoteDevicesByHwid);
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
            p.syncDevices(remoteDevicesByHwid);
            var savedProfile = profileRepository.save(p);
            results.add(profileMapper.toResult(savedProfile, remoteDevicesByHwid));
        });
        return results;
    }

}
