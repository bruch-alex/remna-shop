package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.ProfileManagementAdapter;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.exception.VpnProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProfileSummaryService implements GetProfileSummaryUseCase {

    private final ProfileManagementAdapter userManagementAdapter;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    @Transactional
    public ProfileResult execute(UUID profileId, UUID authUserUuid) {
        log.debug("Getting profile summary for profile {}", profileId);
        var profileInDB = profileRepository.findById(profileId)
                .orElseThrow(VpnProfileNotFoundException::new);

        if (!profileInDB.getUserId().equals(authUserUuid)) {
            throw new AccessDeniedException("Access denied");
        }

        var remoteProfile = userManagementAdapter.getProfileById(profileId);
        Map<String, Device> remoteDevicesByHwid = userManagementAdapter
                .getDevicesByExternalId(remoteProfile.getRemnawaveUserUuid()).stream()
                .collect(Collectors.toMap(Device::getId, Function.identity()));

        profileInDB.merge(remoteProfile);
        profileInDB.syncDevices(remoteDevicesByHwid);
        var savedProfile = profileRepository.save(profileInDB);

        return profileMapper.toResult(savedProfile, remoteDevicesByHwid);
    }

    @Override
    public List<ProfileResult> syncRemoteProfiles(String email, UUID authUserUuid) {
        List<Profile> remoteProfiles = userManagementAdapter.getProfilesByEmail(email);
        List<ProfileResult> results = new ArrayList<>();

        remoteProfiles.forEach(p -> {
            p.setNewUserId(authUserUuid);
            Map<String, Device> remoteDevicesByHwid = userManagementAdapter
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
