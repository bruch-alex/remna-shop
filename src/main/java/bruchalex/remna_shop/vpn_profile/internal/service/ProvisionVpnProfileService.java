package bruchalex.remna_shop.vpn_profile.internal.service;

import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileCommand;
import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileUseCase;
import bruchalex.remna_shop.vpn_profile.internal.domain.VpnProfile;
import bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave.dto.CreateRemnawaveUserRequest;
import bruchalex.remna_shop.vpn_profile.internal.port.VpnProfileRepository;
import bruchalex.remna_shop.vpn_profile.internal.port.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProvisionVpnProfileService implements ProvisionVpnProfileUseCase {
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
