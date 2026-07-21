package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class CreateUserRequest {

    @Builder.Default
    private UUID username = UUID.randomUUID();
    private Instant expireAt;
}
