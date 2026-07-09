package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.domain.TariffRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetNewTrialTariffUseCase {
    private final TariffRepositoryPort tariffRepo;

    @Transactional
    public void execute(UUID newTrialTariffUuid) {
        tariffRepo.findByTrialAndEnabled(true, true)
                .ifPresent(Tariff::disable);

        var newTrialTariff = tariffRepo.findTariffById(newTrialTariffUuid).orElseThrow();

        tariffRepo.flush();

        newTrialTariff.enable();
    }
}
