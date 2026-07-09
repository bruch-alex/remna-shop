package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.domain.TariffRepositoryPort;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewTariffUseCase {

    private final TariffRepositoryPort tariffRepo;
    private final TariffMapper tariffMapper;

    public CreateNewTariffResult execute(CreateNewTariffCommand command) {
        var tariff = Tariff.fromCommand(command);
        var saved = tariffRepo.save(tariff);
        return tariffMapper.toResult(saved);
    }
}
