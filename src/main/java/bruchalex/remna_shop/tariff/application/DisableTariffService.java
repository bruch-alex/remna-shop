package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.application.port.in.web.DisableTariffUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.TariffResult;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisableTariffService implements DisableTariffUseCase {

    private final TariffRepositoryPort tariffRepo;
    private final TariffMapper tariffMapper;

    public TariffResult execute(UUID uuid) {
        var tariff = tariffRepo.findById(uuid).orElseThrow();
        tariff.disable();
        return tariffMapper.toResult(tariff);
    }
}
