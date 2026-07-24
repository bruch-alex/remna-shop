package bruchalex.remna_shop.user.adapter.in.web;

import bruchalex.remna_shop.user.adapter.in.web.dto.LoginUserRequest;
import bruchalex.remna_shop.user.adapter.in.web.dto.LoginUserResponse;
import bruchalex.remna_shop.user.adapter.in.web.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.adapter.in.web.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.application.port.in.web.LoginUserUseCase;
import bruchalex.remna_shop.user.application.port.in.web.RegisterUserUseCase;
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
        var userResponse = registerUserUseCase.execute(request.toCommand());
        return ResponseEntity.ok(RegisterUserResponse.of(userResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest request) {
        var result = loginUserUseCase.execute(request.toCommand());
        return ResponseEntity.ok(LoginUserResponse.of(result));
    }
}
