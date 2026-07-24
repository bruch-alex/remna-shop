package bruchalex.remna_shop.vpn.infra.remnawave.dto;

import java.util.List;

public record DevicesResponse(List<DeviceResponse> devices, Integer total) {}
