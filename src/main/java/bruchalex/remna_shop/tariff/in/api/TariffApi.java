package bruchalex.remna_shop.tariff.in.api;

import bruchalex.remna_shop.tariff.domain.Tariff;

import java.util.Optional;

public interface TariffApi {
    Optional<TariffSummary> getTariff(String id);
}
