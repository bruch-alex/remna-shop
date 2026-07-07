package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.rest.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.rest.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.domain.*;
import bruchalex.remna_shop.user.domain.exception.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public RegisterUserResponse execute(RegisterUserRequest request) {
        log.warn(">>> execute() called for {}", request.email());
        var email = new Email(request.email());

        if (userRepository.existsByEmail(email)) {
            var user =  userRepository.findByEmail(email).orElseThrow();
            throw new UserAlreadyExistsException(email);
        }

        var hashedPassword = new HashedPassword(passwordEncoder.encode(request.password()));
        var newUser = MyUser.create(
                email,
                hashedPassword,
                UserRole.USER
        );
        var saved = userRepository.save(newUser);
        log.debug("Saved user with email {}", saved.getEmail());
        return RegisterUserResponse.from(saved);
    }
}
