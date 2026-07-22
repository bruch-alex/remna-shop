package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class CreateUserRequest {
    private UUID username;
    private Instant expireAt;
}
