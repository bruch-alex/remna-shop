package bruchalex.remna_shop.vpn_profile;

import java.util.UUID;

public record CreateNewVpnProfileResult(
        UUID profileUuid,
        String subscriptionLink,
        Integer deviceLimit,
        Integer trafficLimitGb
) {
}
