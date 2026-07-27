package bruchalex.remna_shop.tariff.application.port.in.web;

import java.util.List;

public interface GetAllTariffsUseCase {
    List<TariffResult> execute(boolean active);
}
