package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.GetTestTariffDetailsUseCase;
import bruchalex.remna_shop.tariff.TariffDetails;
import bruchalex.remna_shop.tariff.domain.TariffRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTestTariffDetailsService implements GetTestTariffDetailsUseCase {

    private final TariffRepositoryAdapter tariffRepo;

    @Override
    public TariffDetails getTrialTariffDetails() {
        return tariffRepo.getCurrentTrialTariff(TariffDetails.class);
    }
}
