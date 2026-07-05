package bruchalex.remna_shop.vpn_profile.in;

import bruchalex.remna_shop.vpn_profile.core.service.VpnProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vpn-profile")
@RequiredArgsConstructor
public class VpnController {
    private final VpnProfileService vpnProfileService;

    @PostMapping
    public ResponseEntity<String> createProfile(@AuthenticationPrincipal User user) {
        vpnProfileService.createTestVpnProfile();
        return ResponseEntity.ok("Profile created");
    }

}
