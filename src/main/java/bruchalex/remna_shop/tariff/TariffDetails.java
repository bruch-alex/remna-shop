package bruchalex.remna_shop.tariff;

import java.time.Duration;

public record TariffDetails(
        Integer id,
        String name,
        Integer devicesAllowed,
        Integer trafficLimitGb,
        Duration durationDays,
        Integer price
) {
}
