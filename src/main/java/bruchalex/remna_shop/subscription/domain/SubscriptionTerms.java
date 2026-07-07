package bruchalex.remna_shop.subscription.domain;

import java.time.Duration;
import java.util.UUID;

public record SubscriptionTerms(
        Integer tariffId,
        Integer price,
        Duration period,
        Integer traffic,
        Integer devicesLimit
) {}
