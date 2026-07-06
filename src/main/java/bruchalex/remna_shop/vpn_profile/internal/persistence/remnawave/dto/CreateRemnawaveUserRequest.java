package bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public class CreateRemnawaveUserRequest {
    @Builder.Default
    private UUID username = UUID.randomUUID();
    private Instant expireAt;
}
