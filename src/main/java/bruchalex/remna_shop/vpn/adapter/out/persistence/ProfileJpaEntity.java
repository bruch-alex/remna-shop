package bruchalex.remna_shop.vpn.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile", schema = "vpn")
@Getter
public class ProfileJpaEntity {

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

    @OneToMany(mappedBy = "profile")
    private List<DeviceLabelJpaEntity> labels;
}
