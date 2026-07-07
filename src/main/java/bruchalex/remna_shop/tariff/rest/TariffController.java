package bruchalex.remna_shop.tariff.rest;

import bruchalex.remna_shop.tariff.TariffDetails;
import bruchalex.remna_shop.tariff.application.CreateNewTariffCommand;
import bruchalex.remna_shop.tariff.application.CreateNewTariffService;
import bruchalex.remna_shop.tariff.application.GetAllTariffsService;
import bruchalex.remna_shop.tariff.rest.dto.CreateNewTariffRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {

    private final GetAllTariffsService getAllTariffsService;
    private final CreateNewTariffService createNewTariffService;

    @GetMapping
    public ResponseEntity<List<TariffDetails>> getTariffs() {
        var response = getAllTariffsService.getAllActiveTariffs();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TariffDetails> addTariff(@RequestBody CreateNewTariffRequest request) {
        var command = new CreateNewTariffCommand(request.name(),request.priceInRubles(),request.deviceLimit(),request.trafficLimitGb());
        var response = createNewTariffService.createTariff(command);
        return ResponseEntity.ok(response);
    }
    // create new tariff (admins)
    // update tariff (admins)
    // delete tariff (admins)
}
