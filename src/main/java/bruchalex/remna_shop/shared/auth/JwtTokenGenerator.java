package bruchalex.remna_shop.shared.auth;

import bruchalex.remna_shop.user.application.port.TokenGenerator;
import bruchalex.remna_shop.user.domain.UserId;
import bruchalex.remna_shop.user.domain.UserRole;
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
    public String generate(UserId userId, UserRole role) {
        return Jwts.builder()
                .subject(userId.value().toString())
                .claim("role", role.getValue())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(properties.expiration())))
                .signWith(properties.secretKey())
                .compact();
    }
}
