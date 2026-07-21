package bruchalex.remna_shop.subscription.domain;

import java.util.UUID;

public record SubscriptionTerms(
        UUID tariffId,
        Integer priceInRubles,
        Integer deviceLimit,
        Integer trafficLimit
) {
}
