package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisableTariffUseCase {

    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    public TariffResult execute(UUID uuid) {
        var tariff = tariffRepo.findById(uuid).orElseThrow();
        tariff.disable();
        return tariffMapper.toResult(tariff);
    }
}
