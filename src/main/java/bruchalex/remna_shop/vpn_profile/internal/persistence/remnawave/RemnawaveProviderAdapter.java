package bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave;

import bruchalex.remna_shop.vpn_profile.internal.domain.VpnProfile;
import bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave.dto.RemnawaveUserResponse;
import bruchalex.remna_shop.vpn_profile.internal.port.VpnProviderPort;
import bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave.dto.CreateRemnawaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RemnawaveProviderAdapter implements VpnProviderPort {

    private final RemnawaveHttpApi remnawaveHttpApi;

    @Override
    public VpnProfile createEmptyVpnProfile() {
        var createRemnawaveUserRequest = CreateRemnawaveUserRequest.builder()
                .username(UUID.randomUUID())
                .expireAt(Instant.now())
                .build();

        var response = remnawaveHttpApi.createUser(createRemnawaveUserRequest);

        return profileFromRemnawaveResponse(response);
    }

    private VpnProfile profileFromRemnawaveResponse(RemnawaveUserResponse response) {
        return VpnProfile.builder()
                .vpnProfileUuid(response.uuid())
                .telegramId(response.telegramId())
                .subscriptionUrl(response.subscriptionUrl())
                .createdAt(response.createdAt())
                .maxDevicesAllowed(response.hwidDeviceLimit())
                .build();
    }
}
