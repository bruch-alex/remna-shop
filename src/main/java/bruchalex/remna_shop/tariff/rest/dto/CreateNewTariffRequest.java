package bruchalex.remna_shop.tariff.rest.dto;

public record CreateNewTariffRequest(
        String name,
        Integer priceInRubles,
        Integer deviceLimit,
        Integer trafficLimitGb
){
}
