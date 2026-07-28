package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.vpn.adapter.in.web.dto.ProfileResponse;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RestMapper {
    ProfileResponse toResponse(GetProfileSummaryUseCase.ProfileResult result);
}
