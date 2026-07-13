package bruchalex.remna_shop.tariff.in.rest.dto;

import java.time.Instant;
import java.util.UUID;

public record TariffResponse(
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
