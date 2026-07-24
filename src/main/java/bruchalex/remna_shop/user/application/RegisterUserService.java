package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.application.port.in.web.RegisterUserUseCase;
import bruchalex.remna_shop.user.application.port.out.persistence.UserRepository;
import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.HashedPassword;
import bruchalex.remna_shop.user.domain.MyUser;
import bruchalex.remna_shop.user.domain.UserRole;
import bruchalex.remna_shop.user.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase.Result execute(RegisterUserUseCase.Command command) {
        var email = new Email(command.email());

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }

        var hashedPassword = new HashedPassword(
                passwordEncoder.encode(command.password())
        );
        var newUser = MyUser.create(email, hashedPassword, UserRole.USER);

        var saved = userRepository.save(newUser);
        return new RegisterUserUseCase.Result(saved.getEmail().value(), saved.getCreatedAt());
    }
}
