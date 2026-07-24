package bruchalex.remna_shop.vpn.infra.remnawave.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserRequest {

    private UUID username;
    private Instant expireAt;
}
