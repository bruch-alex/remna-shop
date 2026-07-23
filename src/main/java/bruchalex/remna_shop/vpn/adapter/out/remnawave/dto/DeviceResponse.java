package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import java.time.Instant;

public record DeviceResponse(
        String hwid,
        Integer userId,
        String platform,
        String osVersion,
        String deviceModel,
        String userAgent,
        String requestIp,
        Instant createdAt,
        Instant updatedAt
) {
}
