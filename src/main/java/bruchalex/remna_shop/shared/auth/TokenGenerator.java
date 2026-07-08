package bruchalex.remna_shop.shared.auth;

import java.util.UUID;

public interface TokenGenerator {
    String generate(UUID userUuid, String userEmail, String userRole);
}
