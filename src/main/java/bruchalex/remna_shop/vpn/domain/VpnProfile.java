package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vpn_profile")
public class VpnProfile {
    @Id
    private UUID id;
    private UUID userUuid;
    private Integer maxDevicesAllowed;
    private String subscriptionUrl;
    private String telegramId;
    private String label;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant expiresAt;
    private Integer trafficLimitGb;
}
