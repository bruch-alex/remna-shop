package bruchalex.remna_shop.tariff.application;

import bruchalex.remna_shop.tariff.TariffDetails;
import bruchalex.remna_shop.tariff.domain.TariffRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTariffsService {
    private final TariffRepositoryAdapter tariffRepositoryAdapter;

    public List<TariffDetails> getAllActiveTariffs() {
        return tariffRepositoryAdapter.getAllActiveTariffs();
    }
}
