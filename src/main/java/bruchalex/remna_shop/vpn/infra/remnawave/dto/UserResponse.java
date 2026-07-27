package bruchalex.remna_shop.vpn.infra.remnawave.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserResponse(
    UUID uuid,
    Integer id,
    String shortUuid,
    UUID username,
    UserStatus status,
    Long trafficLimitBytes,
    TrafficLimitStrategy trafficLimitStrategy,
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
    Integer lastTriggeredThreshold,
    Instant subRevokedAt,
    Instant lastTrafficResetAt,
    Instant createdAt,
    Instant updatedAt,
    String subscriptionUrl,
    List<InternalSquad> activeInternalSquads,
    UserTraffic userTraffic
) {}
