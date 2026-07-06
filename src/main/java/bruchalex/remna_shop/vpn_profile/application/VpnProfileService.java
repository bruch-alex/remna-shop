package bruchalex.remna_shop.vpn_profile.application;

import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileCommand;
import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileUseCase;
import bruchalex.remna_shop.vpn_profile.domain.VpnProfile;
import bruchalex.remna_shop.vpn_profile.infra.remnawave.dto.CreateRemnawaveUserRequest;
import bruchalex.remna_shop.vpn_profile.domain.VpnProfileRepository;
import bruchalex.remna_shop.vpn_profile.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class VpnProfileService implements ProvisionVpnProfileUseCase {
    private final VpnProfileRepository vpnProfileRepository;
    private final VpnProviderPort vpnProvider;

    @Override
    public VpnProfile createVpnProfile(ProvisionVpnProfileCommand command) {
        var createRemnaUserRequest = CreateRemnawaveUserRequest.builder()
                .expireAt(Instant.now())
                .build();

        var vpnProfile = vpnProvider.createEmptyVpnProfile();
        vpnProfile.assignToUser(command.userUuid());
        var saved = vpnProfileRepository.save(vpnProfile);
        return saved;
    }

    // createDisabledVpnProfile()
    // setVpnProfileLimits()
}
