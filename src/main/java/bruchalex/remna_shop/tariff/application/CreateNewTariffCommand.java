package bruchalex.remna_shop.tariff.application;

public record CreateNewTariffCommand(
        String name,
        Integer priceInRubles,
        Integer deviceLimit,
        Integer trafficLimitGb
) {
}
