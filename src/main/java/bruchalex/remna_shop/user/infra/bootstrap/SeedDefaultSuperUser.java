package bruchalex.remna_shop.user.infra.bootstrap;

import bruchalex.remna_shop.user.domain.*;
import bruchalex.remna_shop.user.rest.UserExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class SeedDefaultSuperUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SuperUserProperties appProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserExceptionHandler user;

    @Override
    public void run(String... args) {
        var email = new Email(appProperties.email());

        if (userRepository.existsByEmail(email)){
            return;
        }
        var defaultAdmin = MyUser.create(
                new Email(appProperties.email()),
                new HashedPassword(passwordEncoder.encode(appProperties.password())),
                UserRole.ADMIN
        );

        userRepository.save(defaultAdmin);
    }
}
