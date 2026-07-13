package bruchalex.remna_shop.tariff.in.api;

public record TariffSummary(
        String name,
        boolean trial,
        boolean enabled,
        Integer trafficLimitGb,
        Integer devicesLimit,
        Integer durationDays,
        Integer priceRubles
) {
}
