package bruchalex.remna_shop.vpn_profile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VpnProfile {
    @Id
    private UUID vpnProfileUuid;
    private Integer maxDevicesAllowed;
    private String subscriptionUrl;
    private String telegramId;
    private String label;
    private Instant createdAt;
    private Instant updatedAt;
}
