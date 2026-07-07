package bruchalex.remna_shop.vpn_profile;

import java.time.Instant;

public record CreateNewVpnProfileCommand(
        Instant expireAt,
        Integer deviceLimit,
        Integer trafficLimitGb
) {
}
