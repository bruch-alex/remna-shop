package bruchalex.remna_shop.tariff.infra.bootstrap;


import bruchalex.remna_shop.tariff.application.CreateNewTariffService;
import bruchalex.remna_shop.tariff.application.GetAllTariffsService;
import bruchalex.remna_shop.tariff.application.SetNewTrialTariffService;
import bruchalex.remna_shop.tariff.application.port.out.persistence.TariffRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevTariffSeeder implements CommandLineRunner {

    private final CreateNewTariffService createNewTariffService;
    private final SetNewTrialTariffService setNewTrialTariffService;
    private final GetAllTariffsService getAllTariffsService;

    private final TariffRepositoryPort tariffRepo;

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
        createNewTariffService.execute(command);
        createNewTariffService.execute(commandTrial);

        var tariffs = getAllTariffsService.execute(false);

        for (var tariff : tariffs) {
            if (tariff.name().equals("Trial Tariff")) {
                setNewTrialTariffService.execute(tariff.id());
                break;
            }
        }
    }
}
