package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.shared.auth.AuthUser;
import bruchalex.remna_shop.vpn.adapter.in.web.dto.ProfileResponse;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.application.port.out.VpnConnectivityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

    private final VpnConnectivityPort vpnConnectivityPort;
    private final GetProfileSummaryUseCase getProfileSummaryUseCase;

    private final RestMapper mapper;

    @GetMapping("/check-auth")
    public ResponseEntity<Boolean> isAuthenticated() {
        final var authenticated = vpnConnectivityPort.isAuthenticated();
        return ResponseEntity.ok(authenticated);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable("profileId") UUID profileId,
            @AuthenticationPrincipal AuthUser authUser) {
        var result = getProfileSummaryUseCase.execute(profileId, UUID.fromString(authUser.userUuid()));
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sync")
    public ResponseEntity<List<ProfileResponse>> syncProfile(@AuthenticationPrincipal AuthUser authUser) {
        var result = getProfileSummaryUseCase.syncRemoteProfiles(authUser.userEmail(), UUID.fromString(authUser.userUuid()));
        var response = result.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}
