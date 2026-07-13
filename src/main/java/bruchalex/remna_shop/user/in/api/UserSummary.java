package bruchalex.remna_shop.user.in.api;

import java.time.Instant;
import java.util.UUID;

public record UserSummary(
        UUID id,
        String email,
        UUID vpnProfileUuid,
        String userRole,
        Instant createdAt,
        Instant updatedAt
) {
}
