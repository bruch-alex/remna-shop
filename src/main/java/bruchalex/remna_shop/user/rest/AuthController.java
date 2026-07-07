package bruchalex.remna_shop.user.rest;

import bruchalex.remna_shop.user.rest.dto.LoginUserRequest;
import bruchalex.remna_shop.user.rest.dto.LoginUserResponse;
import bruchalex.remna_shop.user.rest.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.rest.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.application.LoginUserUseCase;
import bruchalex.remna_shop.user.application.RegisterUserUseCase;
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
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        var user = registerUserUseCase.execute(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(loginUserUseCase.execute(request));
    }
}
