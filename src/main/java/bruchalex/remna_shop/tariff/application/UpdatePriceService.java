package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.application.port.in.web.TariffResult;
import bruchalex.remna_shop.tariff.application.port.in.web.UpdateTariffPriceUseCase;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePriceService implements UpdateTariffPriceUseCase {

    private final TariffRepositoryPort tariffRepo;
    private final TariffMapper tariffMapper;

    @Transactional
    @Override
    public TariffResult execute(UpdateTariffPriceUseCase.Command command) {
        var tariff = tariffRepo.findTariffById(command.tariffId()).orElseThrow();

        tariff.setNewPrice(command.newPrice());
        var saved = tariffRepo.save(tariff);
        return tariffMapper.toResult(saved);
    }
}
