package bruchalex.remna_shop.vpn.application.port.in;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface GetProfileSummaryUseCase {

    ProfileResult execute(UUID profileId, UUID authUserUuid);

    record DeviceResult(
            String label,
            String hwid,
            String os,
            String model
    ) {
    }

    record ProfileResult(
            String id,
            String userId,
            String telegramId,
            String label,
            String subscriptionUrl,

            Integer deviceLimit,
            Integer trafficLimitGb,
            Instant expiresAt,

            Integer addedDevices,
            List<DeviceResult> devices
    ) {
    }
}
