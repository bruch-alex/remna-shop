package bruchalex.remna_shop.user.infra.bootstrap;

import bruchalex.remna_shop.shared.AppProperties;
import bruchalex.remna_shop.user.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SeedDefaultSuperUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AppProperties appProperties;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var defaultAdmin = MyUser.builder()
                .uuid(new UserId())
                .email(new Email(appProperties.email()))
                .role(UserRole.ADMIN)
                .hashedPassword(new HashedPassword(passwordEncoder.encode(appProperties.passwordHash())))
                .createdAt(Instant.now())
                .build();

        userRepository.save(defaultAdmin);
    }
}
