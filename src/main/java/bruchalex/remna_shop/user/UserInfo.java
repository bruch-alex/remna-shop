package bruchalex.remna_shop.user;

import java.util.UUID;

public record UserInfo(
        UUID uuid,
        String email
) {
}
