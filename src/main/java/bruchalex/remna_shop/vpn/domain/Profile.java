package bruchalex.remna_shop.vpn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class Profile {
    private UUID uuid;
    private UUID userUuid;

    private Integer deviceLimit;
    private Integer trafficLimitGb;
    private String subscriptionUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant expiresAt;

    private String telegramId;
    private String label;
    private List<Device> devices;
}
