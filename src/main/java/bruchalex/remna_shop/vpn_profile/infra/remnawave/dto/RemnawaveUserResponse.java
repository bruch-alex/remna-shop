package bruchalex.remna_shop.vpn_profile.infra.remnawave.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RemnawaveUserResponse(
        UUID uuid,
        Integer id,
        String shortUuid,
        String username,
        Instant expireAt,
        String telegramId,
        String email,
        String description,
        String tag,
        Integer hwidDeviceLimit,
        UUID externalSquadUuid,
        String trojanPassword,
        UUID vlessUuid,
        String ssPassword,
        Instant subRevokedAt,
        Instant lastTrafficResetAt,
        Instant createdAt,
        Instant updatedAt,
        String subscriptionUrl,
        List<ActiveInternalSquad> activeInternalSquads,
        UserTraffic userTraffic,
        UserStatus status,
        Long usedTrafficBytes,
        RemnawaveTrafficLimitStrategy trafficLimitStrategy,
        Integer lastTriggeredThreshold
) {
}
