package bruchalex.remna_shop.tariff.rest;

import bruchalex.remna_shop.tariff.application.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import bruchalex.remna_shop.tariff.rest.dto.CreateNewTariffRequest;
import bruchalex.remna_shop.tariff.rest.dto.CreateNewTariffResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {
    private final CreateNewTariffUseCase createNewTariffUseCase;
    private final TariffMapper tariffMapper;

    @PostMapping
    public ResponseEntity<CreateNewTariffResponse> createTariff(CreateNewTariffRequest request) {

        var command = tariffMapper.toCommand(request);

        var result = createNewTariffUseCase.execute(command);

        var response = tariffMapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<String>> listTariffs(){
        List<String> list = List.of(
                "tariff1",
                "tariff2"
        );
        return ResponseEntity.ok(list);
    }

    /*
        TODO: decide how to get active tariffs
        GET /tariff?status=active
        or
        GET /tariff/active
        or
        GET /tariff and separate (admin only?) endpoint for getting all tariffs
     */
}
