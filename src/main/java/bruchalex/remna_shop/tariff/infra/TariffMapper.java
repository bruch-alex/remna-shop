package bruchalex.remna_shop.tariff.infra;

import bruchalex.remna_shop.tariff.application.CreateNewTariffCommand;
import bruchalex.remna_shop.tariff.application.TariffResult;
import bruchalex.remna_shop.tariff.domain.Tariff;
import bruchalex.remna_shop.tariff.rest.dto.CreateNewTariffRequest;
import bruchalex.remna_shop.tariff.rest.dto.TariffResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TariffMapper {
    TariffMapper INSTANCE = Mappers.getMapper(TariffMapper.class);

    CreateNewTariffCommand toCommand(CreateNewTariffRequest request);

    TariffResult toResult(Tariff tariff);

    TariffResponse toResponse(TariffResult result);
}
