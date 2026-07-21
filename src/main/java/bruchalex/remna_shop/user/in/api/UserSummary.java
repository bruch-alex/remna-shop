package bruchalex.remna_shop.user.in.api;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record UserSummary(
        UUID id,
        String email,
        String userRole,
        Instant createdAt,
        Instant updatedAt
) {
}
