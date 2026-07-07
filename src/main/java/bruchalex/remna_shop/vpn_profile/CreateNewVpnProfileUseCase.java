package bruchalex.remna_shop.vpn_profile;

import bruchalex.remna_shop.vpn_profile.domain.VpnProfile;

public interface CreateNewVpnProfileUseCase {
    CreateNewVpnProfileResult  createNewVpnProfile(CreateNewVpnProfileCommand command);
}
