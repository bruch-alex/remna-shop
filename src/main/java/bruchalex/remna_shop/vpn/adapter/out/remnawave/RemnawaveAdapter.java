package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.domain.VpnProfile;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemnawaveAdapter implements VpnProviderPort {

    private final RemnawaveRestClient remnawaveRestClient;
    private final RemnawaveMapper remnawaveMapper;

    @Override
    public VpnProfile create(VpnProfile vpnProfile) {
        var request = CreateUserRequest.builder()
                .username(vpnProfile.getId())
                .expireAt(vpnProfile.getExpiresAt())
                .build();
        var response = remnawaveRestClient.createUser(request).response();
        return remnawaveMapper.toVpnProfile(response);
    }
}
