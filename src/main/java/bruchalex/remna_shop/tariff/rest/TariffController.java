package bruchalex.remna_shop.tariff.rest;

import bruchalex.remna_shop.tariff.application.CreateNewTariffUseCase;
import bruchalex.remna_shop.tariff.application.GetAllTariffsUseCase;
import bruchalex.remna_shop.tariff.application.UpdatePriceUseCase;
import bruchalex.remna_shop.tariff.infra.TariffMapper;
import bruchalex.remna_shop.tariff.rest.dto.CreateNewTariffRequest;
import bruchalex.remna_shop.tariff.rest.dto.TariffResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final GetAllTariffsUseCase getAllTariffsUseCase;
    private final UpdatePriceUseCase updatePriceUseCase;

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

        var command = tariffMapper.toCommand(request);

        var result = createNewTariffUseCase.execute(command);

        var response = tariffMapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TariffResponse>> listTariffs(@RequestParam(defaultValue = "true") boolean active) {
        var response = getAllTariffsUseCase.execute(active)
                .stream()
                .map(tariffMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<TariffResponse> updatePrice(@RequestBody Integer price, @PathVariable UUID id) {
        var result = updatePriceUseCase.execute(id, price);
        return ResponseEntity.ok(tariffMapper.toResponse(result));
    }
}
