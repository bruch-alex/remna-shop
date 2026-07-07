package bruchalex.remna_shop.subscription.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
    private boolean paused;
    private UUID userUuid;
    private UUID mainVpnProfileUuid;
    private String mainVpnProfileName;

    private Instant createdAt;
    private Instant expiresAt;

    @Embedded
    private SubscriptionTerms subscriptionTerms;
}
