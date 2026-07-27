package bruchalex.remna_shop.tariff.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateNewTariffRequest(
        @NotBlank String name,
        @NotNull @Positive Integer trafficLimitGb,
        @NotNull @Positive Integer deviceLimit,
        @NotNull @Positive Integer durationDays,
        @NotNull @Positive Integer priceRubles
) {
}
