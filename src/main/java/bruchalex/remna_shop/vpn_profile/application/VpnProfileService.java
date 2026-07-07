package bruchalex.remna_shop.vpn_profile.application;

import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileCommand;
import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileResult;
import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileUseCase;
import bruchalex.remna_shop.vpn_profile.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VpnProfileService implements CreateNewVpnProfileUseCase {
    private final VpnProviderPort vpnProvider;

    @Override
    public CreateNewVpnProfileResult createNewVpnProfile(CreateNewVpnProfileCommand command) {
        var vpnProfile = vpnProvider.createVpnProfile(command);

        return new CreateNewVpnProfileResult(
                vpnProfile.getVpnProfileUuid(),
                vpnProfile.getSubscriptionUrl(),
                vpnProfile.getMaxDevicesAllowed(),
                vpnProfile.getTrafficLimitGb()
        );
    }

}
