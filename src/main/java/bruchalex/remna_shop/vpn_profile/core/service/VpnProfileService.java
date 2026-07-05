package bruchalex.remna_shop.vpn_profile.core.service;

import bruchalex.remna_shop.vpn_profile.core.domain.VpnProfile;
import bruchalex.remna_shop.vpn_profile.core.port.in.CreateTestVpnProfileUseCase;
import bruchalex.remna_shop.vpn_profile.core.port.out.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VpnProfileService implements CreateTestVpnProfileUseCase {

    private final VpnProviderPort remnawaveProviderAdapter;

    @Override
    public VpnProfile createTestVpnProfile(String email, String telegramId) {
        var vpnProfile = remnawaveProviderAdapter.createVpnProfile(email);
        return null;
    }
}
