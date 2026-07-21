package bruchalex.remna_shop.subscription.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Subscription {
    @Id
    private UUID id;

    private UUID userId;
    private UUID vpnProfileId;

    private Instant createdAt;
    private Instant updatedAt;

    @Embedded
    private SubscriptionTerms subscriptionTerms;
}
