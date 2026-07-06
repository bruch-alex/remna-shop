package bruchalex.remna_shop.vpn_profile.internal.domain;

import bruchalex.remna_shop.user.domain.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    private UUID userId;
    private Integer maxDevicesAllowed;
    private String subscriptionUrl;
    private String telegramId;
    private String label;
    private Instant createdAt;
    private Instant updatedAt;

    public void assignToUser(UUID userId) {
        if (this.userId != null) {
            throw new IllegalStateException("User already assigned to vpn profile");
        }
        this.userId = userId;
    }
}
