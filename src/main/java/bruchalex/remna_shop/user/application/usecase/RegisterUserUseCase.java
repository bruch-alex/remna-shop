package bruchalex.remna_shop.user.application.usecase;

import bruchalex.remna_shop.user.application.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.application.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.domain.*;
import bruchalex.remna_shop.user.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserResponse execute(RegisterUserRequest request) {
        var email = new Email(request.email());
        var hashedPassword = new HashedPassword(passwordEncoder.encode(request.password()));

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }

        var newUser = MyUser.create(
                email,
                hashedPassword,
                UserRole.USER
        );

        var saved = userRepository.save(newUser);

        return RegisterUserResponse.from(saved);
    }
}
