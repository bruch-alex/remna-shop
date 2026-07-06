package bruchalex.remna_shop.vpn_profile.internal.persistence.remnawave.dto;

import java.time.Instant;

public record UserTraffic(
        Long usedTrafficBytes,
        Long lifetimeUsedTrafficBytes,
        Instant onlineAt,
        Instant firstConnectedAt,
        Instant lastConnectedNodeUuid
) {
}
