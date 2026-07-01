package bruchalex.remna_shop.user.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public record HashedPassword(String value) {

    public boolean matches(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, value);
    }
}
