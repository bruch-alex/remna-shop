package bruchalex.remna_shop.subscription.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID UserUuid;
    private UUID VpnProfileUuid;


    private Instant createdAt;
    private Instant expiresAt;

    @Embedded
    private SubscriptionTerms subscriptionTerms;
}
