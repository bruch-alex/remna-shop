package bruchalex.remna_shop.user.infra.bootstrap;

import bruchalex.remna_shop.shared.auth.TokenGenerator;
import bruchalex.remna_shop.user.adapter.in.web.UserExceptionHandler;
import bruchalex.remna_shop.user.application.port.out.persistence.UserRepository;
import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.HashedPassword;
import bruchalex.remna_shop.user.domain.ShopUser;
import bruchalex.remna_shop.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(SuperUserProperties.class)
@Slf4j
public class SeedDefaultSuperUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SuperUserProperties superUserProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserExceptionHandler user;
    private final TokenGenerator tokenGenerator;

    @Override
    public void run(String... args) {
        var email = new Email(superUserProperties.email());

        if (userRepository.existsByEmail(email)) {
            return;
        }
        var defaultAdmin = ShopUser.create(
                new Email(superUserProperties.email()),
                new HashedPassword(passwordEncoder.encode(superUserProperties.password())),
                UserRole.ADMIN
        );

        var saved = userRepository.save(defaultAdmin);
        String token = tokenGenerator.generate(saved.getUuid().value(), saved.getEmail().value(), saved.getRole().getValue());
        log.info("Created token:\n\n{}\n\n", token);
    }
}
