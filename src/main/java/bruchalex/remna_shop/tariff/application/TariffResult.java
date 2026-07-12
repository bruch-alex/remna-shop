package bruchalex.remna_shop.tariff.application;

import java.time.Instant;
import java.util.UUID;

public record TariffResult(
        UUID id,
        String name,
        boolean trial,
        boolean enabled,

        Integer trafficLimitGb,
        Integer devicesLimit,
        Integer durationDays,
        Integer priceRubles,

        Instant createdAt,
        Instant updatedAt
) {
}
