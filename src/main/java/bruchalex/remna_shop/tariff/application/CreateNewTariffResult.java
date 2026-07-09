package bruchalex.remna_shop.tariff.application;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record CreateNewTariffResult(
        UUID id,
        String name,
        boolean trial,
        boolean enabled,

        Short trafficLimitGb,
        Byte devicesLimit,
        Duration durationDays,
        Short priceRubles,

        Instant createdAt,
        Instant updatedAt
) {
}
