package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.TariffDetails;
import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.domain.TariffRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewTariffService {

    private final TariffRepositoryAdapter tariffRepo;

    public TariffDetails createTariff(CreateNewTariffCommand command) {
        var newTariff =  Tariff.builder()
                .name(command.name())
                .price(command.priceInRubles())
                .maxDevicesAllowed(command.deviceLimit())
                .trafficLimitGigabytes(command.trafficLimitGb())
                .build();

        var saved = tariffRepo.save(newTariff);
        return fromTariff(saved);
    }

    private TariffDetails fromTariff(Tariff tariff) {
        return new TariffDetails(
                tariff.getId(),
                tariff.getName(),
                tariff.getMaxDevicesAllowed(),
                tariff.getTrafficLimitGigabytes(),
                tariff.getDuration(),
                tariff.getPrice()
        );
    }
}
