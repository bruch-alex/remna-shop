package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.ProfileManagementAdapter;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        var remoteDevices = userManagementAdapter.getDevicesByProfileId(profileId);

        profile.merge(remoteProfile);
        profile.merge(remoteDevices);
        var savedProfile = profileRepository.save(profile);
        System.out.println("Saved profile: " + savedProfile);
        return profileMapper.toResult(savedProfile);
    }

}
