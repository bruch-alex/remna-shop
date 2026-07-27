package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.vpn.adapter.in.web.dto.ProfileResponse;
import bruchalex.remna_shop.vpn.application.ProfileMapper;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.application.port.out.VpnConnectivityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

    private final VpnConnectivityPort vpnConnectivityPort;
    private final GetProfileSummaryUseCase getProfileSummaryUseCase;

    private final ProfileMapper profileMapper;

    @GetMapping("/check-auth")
    public ResponseEntity<Boolean> isAuthenticated() {
        final var authenticated = vpnConnectivityPort.isAuthenticated();
        return ResponseEntity.ok(authenticated);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("profileId") UUID profileId) {
        var result = getProfileSummaryUseCase.execute(profileId);
        var response = profileMapper.toResponse(result);
        System.out.println("Profile response: " + response);
        return ResponseEntity.ok(response);
    }
}
