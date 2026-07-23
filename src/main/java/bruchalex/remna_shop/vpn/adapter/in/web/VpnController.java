package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.vpn.domain.VpnConnectivityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

    private final VpnConnectivityPort vpnConnectivityPort;

    @GetMapping("/check-auth")
    public ResponseEntity<Boolean> isAuthenticated() {
        var authenticated = vpnConnectivityPort.isAuthenticated();
        return ResponseEntity.ok(authenticated);
    }
}
