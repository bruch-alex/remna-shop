package bruchalex.remna_shop.vpn_profile;

import bruchalex.remna_shop.vpn_profile.internal.domain.VpnProfile;

public interface ProvisionVpnProfileUseCase {
    VpnProfile createVpnProfile(ProvisionVpnProfileCommand command);
}
