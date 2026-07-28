package bruchalex.remna_shop.vpn.adapter.in.web.dto;

import java.time.Instant;
import java.util.List;

public record ProfileResponse(
        String id,
        String userId,
        String telegramId,
        String label,
        String subscriptionUrl,

        Integer trafficLimitGb,
        Instant expiresAt,
        Integer deviceLimit,

        Integer addedDevices,
        List<DeviceResponse> devices

) {
}
