package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.domain.VpnProfile;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import bruchalex.remna_shop.vpn.in.api.VpnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VpnServiceImpl implements VpnService {
    private final VpnProviderPort vpnProvider;

    @Override
    public UUID createDisabledProfile(UUID userId) {
        var vpnProfile = VpnProfile.builder()
                .id(UUID.randomUUID())
                .userUuid(userId)
                .maxDevicesAllowed(1)
                .expiresAt(Instant.now().minusSeconds(1))
                .build();
        var createdProfile = vpnProvider.create(vpnProfile);
        return createdProfile.getId();
    }
}
