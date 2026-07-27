package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.application.port.in.web.GetAllTariffsUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.TariffResult;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTariffsService implements GetAllTariffsUseCase {

    private final TariffRepositoryPort tariffRepo;
    private final TariffMapper tariffMapper;

    @Override
    public List<TariffResult> execute(boolean active) {
        return tariffRepo.findAllByEnabled(active)
                .stream()
                .map(tariffMapper::toResult)
                .toList();
    }
}
