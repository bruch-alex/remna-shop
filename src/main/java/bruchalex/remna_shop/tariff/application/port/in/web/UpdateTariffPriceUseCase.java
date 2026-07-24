package bruchalex.remna_shop.tariff.application.port.in.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public interface UpdateTariffPriceUseCase {

    TariffResult execute(Command command);

    record Command(
            @NotNull
            UUID tariffId,
            @NotNull
            @Positive
            Integer newPrice) {
    }
}
