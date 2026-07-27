package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.application.port.in.web.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.TariffResult;
import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewTariffService implements CreateNewTariffUseCase {

    private final TariffRepositoryPort tariffRepo;
    private final TariffMapper tariffMapper;

    public TariffResult execute(CreateNewTariffUseCase.Command command) {
        var tariff = Tariff.of(command);
        var saved = tariffRepo.save(tariff);
        return tariffMapper.toResult(saved);
    }
}
