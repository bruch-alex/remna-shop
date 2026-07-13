package bruchalex.remna_shop.tariff.in.api;

import java.util.Optional;

public interface TariffLookup {
    Optional<TariffSummary> findTariff(String id);
}
