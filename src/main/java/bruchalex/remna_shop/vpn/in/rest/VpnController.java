package bruchalex.remna_shop.vpn.in.rest;

import bruchalex.remna_shop.vpn.domain.VpnProfile;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vpn-profile")
public class VpnController {

    private final VpnProviderPort vpnProviderPort;

    @PostMapping
    public ResponseEntity<VpnProfile> createVpnProfile(@RequestBody CreateVpnProfileRequest request) {
        VpnProfile newProfile = VpnProfile.builder()
                .id(request.vpnProfileId())
                .userUuid(request.userId())
                .expiresAt(Instant.now())
                .build();

        var profile = vpnProviderPort.create(newProfile);
        return ResponseEntity.ok(profile);
    }
}
