package bruchalex.remna_shop.user.in.rest;

import bruchalex.remna_shop.user.application.LoginUserUseCase;
import bruchalex.remna_shop.user.application.RegisterUserUseCase;
import bruchalex.remna_shop.user.in.rest.dto.LoginUserRequest;
import bruchalex.remna_shop.user.in.rest.dto.LoginUserResponse;
import bruchalex.remna_shop.user.in.rest.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.in.rest.dto.RegisterUserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirements
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        var userResponse = registerUserUseCase.execute(request);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(loginUserUseCase.execute(request));
    }
}