package bruchalex.remna_shop.shared.auth;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(String secret, Duration expiration) {

    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
