package bruchalex.remna_shop.vpn_profile.infra.remnawave.dto;

import java.time.Instant;

public record UserTraffic(
        Long usedTrafficBytes,
        Long lifetimeUsedTrafficBytes,
        Instant onlineAt,
        Instant firstConnectedAt,
        Instant lastConnectedNodeUuid
) {
}
