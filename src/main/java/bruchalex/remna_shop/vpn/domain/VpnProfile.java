package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vpn.vpn_profile")
@Getter
public class VpnProfile {
    @Id
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
}
