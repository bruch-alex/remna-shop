package bruchalex.remna_shop.tariff.infra.bootstrap;


import bruchalex.remna_shop.tariff.application.CreateNewTariffCommand;
import bruchalex.remna_shop.tariff.application.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.application.GetAllTariffsUseCase;
import bruchalex.remna_shop.tariff.application.SetNewTrialTariffUseCase;
import bruchalex.remna_shop.tariff.domain.TariffRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevTariffSeeder implements CommandLineRunner {

    private final CreateNewTariffUseCase createNewTariffUseCase;
    private final SetNewTrialTariffUseCase setNewTrialTariffUseCase;
    private final GetAllTariffsUseCase getAllTariffsUseCase;

    private final TariffRepo tariffRepo;

    @Override
    public void run(String... args) throws Exception {

        if (!tariffRepo.findAll().isEmpty()){
            return;
        }

        var command = new CreateNewTariffCommand(
                "Test Tariff",
                100,
                10,
                30,
                500
        );

        var commandTrial = new CreateNewTariffCommand(
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
