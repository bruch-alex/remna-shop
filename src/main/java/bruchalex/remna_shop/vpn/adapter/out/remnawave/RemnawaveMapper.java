package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.DeviceResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RemnawaveMapper {
    @Mapping(target = "id", source = "username")
    @Mapping(target = "deviceLimit", source = "hwidDeviceLimit")
    @Mapping(target = "label", source = "description")
    @Mapping(target = "expiresAt", source = "expireAt")
    @Mapping(
            target = "trafficLimitGb",
            source = "trafficLimitBytes",
            qualifiedByName = "bytesToGb"
    )
    Profile toVpnProfile(UserResponse response);

    @Named("bytesToGb")
    default Integer bytesToGb(Long bytes) {
        return bytes == null ? null : (int) (bytes / (1024L * 1024L * 1024L));
    }

    @Mapping(target = "id", source = "hwid")
    @Mapping(target = "model", source = "deviceModel")
    @Mapping(target = "os", source = "platform")
    @Mapping(target = "userAgent", source = "userAgent")
    Device toDevice(DeviceResponse response);

}
