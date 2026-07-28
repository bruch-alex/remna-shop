package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.ProfileManagementAdapter;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import bruchalex.remna_shop.vpn.domain.Device;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public ProfileResult execute(UUID profileId) {
        log.debug("Getting profile summary for profile {}", profileId);
        var profile = profileRepository.findById(profileId)
                .orElse(userManagementAdapter.getProfileById(profileId));

        var remoteProfile = userManagementAdapter.getProfileById(profileId);
        Map<String, Device> remoteDevicesByHwid = userManagementAdapter
                .getDevicesByProfileId(profileId).stream()
                .collect(Collectors.toMap(Device::getId, Function.identity()));

        profile.merge(remoteProfile);
        profile.syncDevices(remoteDevicesByHwid);
        var savedProfile = profileRepository.save(profile);

        return profileMapper.toResult(savedProfile, remoteDevicesByHwid);
    }

}
