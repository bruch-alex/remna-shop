package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.UserResponse;
import bruchalex.remna_shop.vpn.domain.VpnProfile;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RemnawaveMapper {

    @Mapping(target = "id", source = "username")
    @Mapping(target = "maxDevicesAllowed", source = "hwidDeviceLimit")
    @Mapping(target = "label", source = "description")
    @Mapping(target = "expiresAt", source = "expireAt")
    @Mapping(target = "trafficLimitGb", source = "trafficLimitBytes", qualifiedByName = "bytesToGb")
    VpnProfile toVpnProfile(UserResponse response);

    @Named("bytesToGb")
    default Integer bytesToGb(Long bytes) {
        return bytes == null ? null : (int) (bytes / (1024L * 1024L * 1024L));
    }
}
