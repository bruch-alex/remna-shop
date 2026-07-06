package bruchalex.remna_shop.vpn_profile.internal.web;

import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileCommand;
import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vpn-profile")
@RequiredArgsConstructor
public class VpnController {

    private final ProvisionVpnProfileUseCase vpnProfileService;

    @PostMapping
    public ResponseEntity<String> createProfile(@AuthenticationPrincipal User user) {
        var uuid = UUID.fromString(user.getUsername());
        var profile = vpnProfileService.createVpnProfile(new ProvisionVpnProfileCommand(uuid));
        return ResponseEntity.ok(profile);
    }

}
