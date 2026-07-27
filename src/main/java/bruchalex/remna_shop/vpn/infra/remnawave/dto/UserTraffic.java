package bruchalex.remna_shop.vpn.infra.remnawave.dto;

import java.time.Instant;
import java.util.UUID;

public record UserTraffic(
        Long usedTrafficBytes,
        Long lifetimeUsedTrafficBytes,
        Instant onlineAt,
        Instant firstConnectedAt,
        UUID lastConnectedNodeUuid
) {
}
