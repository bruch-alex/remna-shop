package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.vpn.adapter.in.web.dto.DeviceResponse;
import bruchalex.remna_shop.vpn.adapter.in.web.dto.ProfileResponse;
import bruchalex.remna_shop.vpn.application.port.in.DeviceManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.in.ProfileManagementUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RestMapper {

    ProfileResponse toResponse(ProfileManagementUseCase.ProfileResult result);

    DeviceResponse toResponse(DeviceManagementUseCase.DeviceResult result);
}
