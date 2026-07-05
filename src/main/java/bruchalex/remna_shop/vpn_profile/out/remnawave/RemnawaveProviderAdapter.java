package bruchalex.remna_shop.vpn_profile.out.remnawave;

import bruchalex.remna_shop.vpn_profile.core.domain.VpnProfile;
import bruchalex.remna_shop.vpn_profile.core.port.out.VpnProviderPort;
import bruchalex.remna_shop.vpn_profile.out.remnawave.dto.CreateRemnawaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class RemnawaveProviderAdapter implements VpnProviderPort {

    private final RemnawaveHttpApi remnawaveHttpApi;

    @Override
    public VpnProfile createVpnProfile(String email, Instant expirationDate) {
        var request = new CreateRemnawaveUserRequest("user_" + Instant.now().toString(),);

        var response = remnawaveHttpApi.createRemnawaveUser(request);

        return VpnProfile.fromResponse(response);
    }
}
