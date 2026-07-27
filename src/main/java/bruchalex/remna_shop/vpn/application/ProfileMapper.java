package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.adapter.in.web.dto.ProfileResponse;
import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.ProfileDeviceLinkTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProfileMapper {

    @Mapping(target = "devices", source = "deviceLabels")
    GetProfileSummaryUseCase.ProfileResult toResult(Profile profile);

    @Mapping(target = "hwid", source = "device.id")
    @Mapping(target = "os", source = "device.os")
    @Mapping(target = "model", source = "device.model")
    @Mapping(target = "label", source = "label")
    GetProfileSummaryUseCase.DeviceResult toDeviceResult(ProfileDeviceLinkTable link);

    ProfileResponse toResponse(GetProfileSummaryUseCase.ProfileResult result);
}
