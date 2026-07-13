package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTariffsUseCase {
    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    public List<TariffResult> execute(boolean active) {
        return tariffRepo.findAllByEnabled(active)
                .stream()
                .map(tariffMapper::toResult)
                .toList();
    }
}
