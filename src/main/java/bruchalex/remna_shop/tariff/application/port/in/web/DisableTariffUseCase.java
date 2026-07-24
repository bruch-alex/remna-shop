package bruchalex.remna_shop.tariff.application.port.in.web;

import java.util.UUID;

public interface DisableTariffUseCase {

    TariffResult execute(UUID tariffUuid);
}
