package bruchalex.remna_shop.vpn_profile.out.remnawave.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public class CreateRemnawaveUserRequest {
    @Builder.Default
    private String username = "user_" + Instant.now().toString();
    private Instant expireAt;
}
