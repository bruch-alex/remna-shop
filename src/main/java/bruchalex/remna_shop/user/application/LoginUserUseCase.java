package bruchalex.remna_shop.user.application.usecase;

import bruchalex.remna_shop.user.web.dto.LoginUserRequest;
import bruchalex.remna_shop.user.web.dto.LoginUserResponse;
import bruchalex.remna_shop.shared.auth.TokenGenerator;
import bruchalex.remna_shop.user.domain.*;
import bruchalex.remna_shop.user.domain.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public LoginUserResponse execute(LoginUserRequest request) {
        var email = new Email(request.email());

        var user = userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getHashedPassword().value())) {
            throw new InvalidCredentialsException();
        }

        var token = tokenGenerator.generate(user.getUuid().toString(), user.getRole().getValue());

        return new LoginUserResponse(token);
    }
}
