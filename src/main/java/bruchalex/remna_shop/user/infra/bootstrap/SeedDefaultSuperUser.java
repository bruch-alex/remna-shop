package bruchalex.remna_shop.user.infra.bootstrap;

import bruchalex.remna_shop.user.adapter.in.web.UserExceptionHandler;
import bruchalex.remna_shop.user.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(SuperUserProperties.class)
public class SeedDefaultSuperUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SuperUserProperties superUserProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserExceptionHandler user;

    @Override
    public void run(String... args) {
        var email = new Email(superUserProperties.email());

        if (userRepository.existsByEmail(email)) {
            return;
        }
        var defaultAdmin = MyUser.create(
                new Email(superUserProperties.email()),
                new HashedPassword(passwordEncoder.encode(superUserProperties.password())),
                UserRole.ADMIN
        );

        userRepository.save(defaultAdmin);
    }
}
