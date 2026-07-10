package bruchalex.remna_shop.tariff.application;

public record CreateNewTariffCommand(
        String name,
        Integer trafficLimitGb,
        Integer devicesLimit,
        Integer durationDays,
        Integer priceRubles
) {
}
