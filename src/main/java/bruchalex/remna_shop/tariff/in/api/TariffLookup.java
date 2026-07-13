package bruchalex.remna_shop.tariff.in.api;

import java.util.Optional;
import java.util.UUID;

public interface TariffLookup {
    Optional<TariffSummary> findTariff(UUID id);

    boolean exists(UUID id);
}
