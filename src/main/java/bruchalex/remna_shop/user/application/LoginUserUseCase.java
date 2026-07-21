package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.shared.auth.TokenGenerator;
import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.user.domain.exception.InvalidCredentialsException;
import bruchalex.remna_shop.user.in.rest.dto.LoginUserRequest;
import bruchalex.remna_shop.user.in.rest.dto.LoginUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    // https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html#authentication-responses
    public LoginUserResponse execute(LoginUserRequest request) {
        var email = new Email(request.email());
        var candidate = userRepository.findByEmail(email);

        var hashToVerify = candidate
                .map(u -> u.getHashedPassword().value())
                .orElse(null);

        boolean passwordMatches = passwordEncoder.matches(request.password(), hashToVerify);

        if (candidate.isEmpty() || !passwordMatches) {
            throw new InvalidCredentialsException();
        }

        var user = candidate.get();
        var token = tokenGenerator.generate(
                user.getUuid().value(), user.getEmail().value(), user.getRole().getValue());

        return new LoginUserResponse(token);
    }
}
