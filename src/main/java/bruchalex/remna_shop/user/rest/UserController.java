package bruchalex.remna_shop.user.rest;

import bruchalex.remna_shop.shared.auth.AuthUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @GetMapping("/status")
    public ResponseEntity<String> me(@AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok("Authenticated. user:" + authUser);
    }
}
