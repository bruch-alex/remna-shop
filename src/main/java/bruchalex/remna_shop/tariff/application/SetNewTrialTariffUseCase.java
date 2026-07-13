package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.domain.TariffRepo;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetNewTrialTariffUseCase {

    private final TariffRepo tariffRepo;
    private final TariffMapper tariffMapper;

    @Transactional
    public TariffResult execute(UUID newTrialTariffUuid) {
        tariffRepo.findByTrialAndEnabled(true, true)
                .ifPresent(Tariff::disable);

        var newTrialTariff = tariffRepo.findTariffById(newTrialTariffUuid).orElseThrow();

        tariffRepo.flush();

        newTrialTariff.enable();
        return tariffMapper.toResult(newTrialTariff);
    }
}
