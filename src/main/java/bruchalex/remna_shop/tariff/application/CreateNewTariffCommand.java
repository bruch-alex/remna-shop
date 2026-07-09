package bruchalex.remna_shop.tariff.application;

import java.time.Duration;

public record CreateNewTariffCommand(
        String name,
        Short trafficLimitGb,
        Byte devicesLimit,
        Duration durationDays,
        Short priceRubles
) {
}
