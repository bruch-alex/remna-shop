package bruchalex.remna_shop.vpn.adapter.in.web.dto;

import java.util.List;
import java.util.UUID;

public record ProfileResponse(
        UUID id,
        Integer deviceLimit,
        List<DeviceResponse> devices

) {
}
