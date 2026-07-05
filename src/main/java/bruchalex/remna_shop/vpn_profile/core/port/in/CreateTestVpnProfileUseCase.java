package bruchalex.remna_shop.vpn_profile.core.port.in;

import bruchalex.remna_shop.vpn_profile.core.domain.VpnProfile;

public interface CreateTestVpnProfileUseCase {
    VpnProfile createTestVpnProfile(String email, String telegramId);
}
