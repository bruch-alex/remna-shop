package bruchalex.remna_shop.shared.auth.internal;

import bruchalex.remna_shop.shared.auth.JwtProperties;
import bruchalex.remna_shop.shared.auth.TokenGenerator;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator {
    private final JwtProperties properties;

    @Override
    public String generate(String userId, String userRole) {
        return Jwts.builder()
                .subject(userId)
                .claim("role", userRole)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(properties.expiration())))
                .signWith(properties.secretKey())
                .compact();
    }
}
