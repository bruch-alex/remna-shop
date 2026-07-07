package bruchalex.remna_shop.vpn_profile.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class VpnProfile {
    private UUID vpnProfileUuid;
    private Integer maxDevicesAllowed;
    private String subscriptionUrl;
    private String telegramId;
    private String label;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer trafficLimitGb;
}
