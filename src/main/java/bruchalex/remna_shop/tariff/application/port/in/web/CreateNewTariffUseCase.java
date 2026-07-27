package bruchalex.remna_shop.tariff.application.port.in.web;

public interface CreateNewTariffUseCase {
    TariffResult execute(Command command);

    record Command(
            String name,
            Integer trafficLimitGb,
            Integer deviceLimit,
            Integer durationDays,
            Integer priceRubles
    ) {
    }
}
