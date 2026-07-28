package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.application.port.in.GetProfileSummaryUseCase;
import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.DeviceLabel;
import bruchalex.remna_shop.vpn.domain.Profile;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProfileMapper {

    @Mapping(target = "devices", expression = "java(mapDevices(profile.getDeviceLabels(), remoteDevicesByHwid))")
    @Mapping(target = "addedDevices", expression = "java(profile.getDeviceLabels().size())")
    GetProfileSummaryUseCase.ProfileResult toResult(Profile profile, @Context Map<String, Device> remoteDevicesByHwid);

    default List<GetProfileSummaryUseCase.DeviceResult> mapDevices(List<DeviceLabel> labels, Map<String, Device> remoteDevicesByHwid) {
        return labels.stream()
                .map(label -> {
                    var remoteDevice = remoteDevicesByHwid.get(label.getDeviceId());
                    return new GetProfileSummaryUseCase.DeviceResult(
                            label.getLabel(),
                            label.getDeviceId(),
                            remoteDevice.getOs(),
                            remoteDevice.getModel()
                    );
                })
                .toList();
    }

}
