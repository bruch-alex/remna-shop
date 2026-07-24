package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.shared.auth.TokenGenerator;
import bruchalex.remna_shop.user.application.port.in.web.LoginUserUseCase;
import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.application.port.out.persistence.UserRepository;
import bruchalex.remna_shop.user.domain.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService implements LoginUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    // https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html#authentication-responses
    public LoginUserUseCase.Result execute(LoginUserUseCase.Command command) {
        var email = new Email(command.email());
        var candidate = userRepository.findByEmail(email);

        var hashToVerify = candidate
                .map(u -> u.getHashedPassword().value())
                .orElse(null);

        boolean passwordMatches = passwordEncoder.matches(
                command.password(),
                hashToVerify
        );

        if (candidate.isEmpty() || !passwordMatches) {
            throw new InvalidCredentialsException();
        }

        var user = candidate.get();
        var token = tokenGenerator.generate(
                user.getUuid().value(),
                user.getEmail().value(),
                user.getRole().getValue()
        );

        return new LoginUserUseCase.Result(token);
    }
}
