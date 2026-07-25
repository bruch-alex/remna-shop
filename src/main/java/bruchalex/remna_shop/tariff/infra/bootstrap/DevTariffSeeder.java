package bruchalex.remna_shop.tariff.infra.bootstrap;


import bruchalex.remna_shop.tariff.application.port.in.web.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.GetAllTariffsUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.SetNewTrialTariffUseCase;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevTariffSeeder implements CommandLineRunner {

    private final CreateNewTariffUseCase createNewTariffUseCase;
    private final SetNewTrialTariffUseCase setNewTrialTariffUseCase;
    private final GetAllTariffsUseCase getAllTariffsUseCase;

    private final TariffRepositoryPort tariffRepo;

    @Override
    public void run(String... args) throws Exception {

        if (!tariffRepo.findAll().isEmpty()) {
            return;
        }

        var command = new CreateNewTariffUseCase.Command(
                "Test Tariff",
                100,
                10,
                30,
                500
        );

        var commandTrial = new CreateNewTariffUseCase.Command(
                "Trial Tariff",
                69,
                3,
                7,
                100
        );
        createNewTariffUseCase.execute(command);
        createNewTariffUseCase.execute(commandTrial);

        var tariffs = getAllTariffsUseCase.execute(false);

        for (var tariff : tariffs) {
            if (tariff.name().equals("Trial Tariff")) {
                setNewTrialTariffUseCase.execute(tariff.id());
                break;
            }
        }
    }
}
