package bruchalex.remna_shop.tariff.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.Duration;

public record CreateNewTariffRequest(
        @JsonProperty(required = true)
        @Size(min = 5)
        String name,

        @JsonProperty(required = true)
        @Min(1)
        Short trafficLimitGb,

        @JsonProperty(required = true)
        @Min(1)
        Byte devicesLimit,

        @JsonProperty(required = true)
        @Min(1)
        Duration durationDays,

        @JsonProperty(required = true)
        @Min(1)
        Short priceRubles
) {
}
