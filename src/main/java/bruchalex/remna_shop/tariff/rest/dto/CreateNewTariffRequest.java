package bruchalex.remna_shop.tariff.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateNewTariffRequest(
        @NotBlank String name,
        @NotNull @Positive Integer trafficLimitGb,
        @NotNull @Positive Integer devicesLimit,
        @NotNull @Positive Integer durationDays,
        @NotNull @Positive Integer priceRubles
) {
}
