package bruchalex.remna_shop.user.application.port;

import bruchalex.remna_shop.user.domain.UserId;
import bruchalex.remna_shop.user.domain.UserRole;

public interface TokenGenerator {
    String generate(UserId userId, UserRole role);
}
