package bruchalex.remna_shop.subscription.domain;

import java.time.Duration;
import java.util.UUID;

public record SubscriptionTerms(
        UUID planId,
        Integer price,
        Duration period
) {}
