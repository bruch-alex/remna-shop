package bruchalex.remna_shop.vpn_profile.domain;

import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileCommand;

public interface VpnProviderPort {
    VpnProfile createVpnProfile(CreateNewVpnProfileCommand command);

}
