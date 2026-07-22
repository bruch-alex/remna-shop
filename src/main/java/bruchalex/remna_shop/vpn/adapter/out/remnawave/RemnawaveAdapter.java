package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.AuthResponse;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveApiException;
import bruchalex.remna_shop.vpn.domain.VpnProfile;
import bruchalex.remna_shop.vpn.domain.VpnProviderException;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemnawaveAdapter implements VpnProviderPort {

    private final RemnawaveRestClient remnawaveRestClient;
    private final RemnawaveMapper remnawaveMapper;

    @Override
    public AuthResponse getAuthStatus() {
        return remnawaveRestClient.getAuthStatus().response();
    }

    @Override
    public VpnProfile create(VpnProfile vpnProfile) {
        var request = CreateUserRequest.builder().username(vpnProfile.getUuid()).expireAt(vpnProfile.getExpiresAt()).build();
        try {
            var response = remnawaveRestClient.createUser(request).response();
            return remnawaveMapper.toVpnProfile(response);
        } catch (RemnawaveApiException _) {
            throw new VpnProviderException();
        }
    }

    @Override
    public List<VpnProfile> getVpnProfileByTelegramId(String telegramId) {
        try {
            return remnawaveRestClient.getUserByTelegramId(telegramId).response().stream().map(remnawaveMapper::toVpnProfile).toList();
        } catch (RemnawaveApiException e) {
            throw new VpnProviderException();
        }
    }
}
