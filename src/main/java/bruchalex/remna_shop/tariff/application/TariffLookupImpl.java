package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.in.api.TariffLookup;
import bruchalex.remna_shop.tariff.in.api.TariffSummary;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TariffLookupImpl implements TariffLookup {

    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    @Override
    public Optional<TariffSummary> findTariff(UUID id) {
        return tariffRepo.findTariffById(id)
                .map(tariffMapper::toSummary);
    }

    @Override
    public boolean exists(UUID id) {
        return tariffRepo.existsById(id);
    }
}
