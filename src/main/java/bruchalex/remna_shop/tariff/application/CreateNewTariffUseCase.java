package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewTariffUseCase {

    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    public TariffResult execute(CreateNewTariffCommand command) {
        var tariff = Tariff.fromCommand(command);
        var saved = tariffRepo.save(tariff);
        return tariffMapper.toResult(saved);
    }
}
