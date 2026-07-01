package bruchalex.remna_shop.user.interfaces.rest;

import bruchalex.remna_shop.user.application.dto.LoginUserRequest;
import bruchalex.remna_shop.user.application.dto.LoginUserResponse;
import bruchalex.remna_shop.user.application.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.application.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.application.usecase.LoginUserUseCase;
import bruchalex.remna_shop.user.application.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(registerUserUseCase.execute(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(loginUserUseCase.execute(request));
    }
}
