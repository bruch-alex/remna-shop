package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import java.util.List;

public record DevicesResponse(
        List<DeviceResponse> devices,
        Integer total
) {
}
