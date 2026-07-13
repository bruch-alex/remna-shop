package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePriceUseCase {
    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    @Transactional
    public TariffResult execute(UUID tariffId, Integer newPrice) {
        var tariff = tariffRepo.findTariffById(tariffId).orElseThrow();

        tariff.setNewPrice(newPrice);
        var saved = tariffRepo.save(tariff);
        return tariffMapper.toResult(saved);
    }
}
