package bruchalex.remna_shop.subscription.rest.dto;

public record SubscriptionResponse(
    String url,
    Integer trafficLimitGb,
    Integer deviceLimit
) {
}
