package bruchalex.remna_shop.tariff.adapter.in.web;

import bruchalex.remna_shop.tariff.application.*;
import bruchalex.remna_shop.tariff.application.port.in.web.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.application.port.in.web.UpdateTariffPriceUseCase;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import bruchalex.remna_shop.tariff.adapter.in.web.dto.CreateNewTariffRequest;
import bruchalex.remna_shop.tariff.adapter.in.web.dto.TariffResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {

    private final TariffMapper tariffMapper;

    private final CreateNewTariffUseCase createNewTariffUseCase;
    private final GetAllTariffsService getAllTariffsService;
    private final UpdatePriceService updatePriceService;
    private final DisableTariffService disableTariffUseCase;
    private final SetNewTrialTariffService setNewTrialTariffService;

    @Operation(
            summary = "Create new tariff",
            description = "Requires ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "403", description = "Caller is not an admin")
    })
    @PostMapping
    public ResponseEntity<TariffResponse> createTariff(@Valid CreateNewTariffRequest request) {

        var result = createNewTariffUseCase.execute(tariffMapper.toCommand(request));

        var response = tariffMapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @SecurityRequirements
    public ResponseEntity<List<TariffResponse>> listTariffs(@RequestParam(defaultValue = "true") boolean active) {
        var response = getAllTariffsService.execute(active)
                .stream()
                .map(tariffMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<TariffResponse> updatePrice(@RequestBody Integer price, @PathVariable UUID id) {
        var command = new UpdateTariffPriceUseCase.Command(id, price);
        var result = updatePriceService.execute(command);
        return ResponseEntity.ok(tariffMapper.toResponse(result));
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<TariffResponse> disable(@PathVariable UUID id) {
        var result = disableTariffUseCase.execute(id);
        return ResponseEntity.ok(tariffMapper.toResponse(result));
    }

    @PutMapping("/{id}/trial")
    public ResponseEntity<TariffResponse> setNewTrialTariff(@PathVariable UUID id) {
        var result = setNewTrialTariffService.execute(id);
        return ResponseEntity.ok(tariffMapper.toResponse(result));
    }
}
