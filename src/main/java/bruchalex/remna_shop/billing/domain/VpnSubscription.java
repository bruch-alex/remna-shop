package bruchalex.remna_shop.billing.domain;

import java.time.Instant;
import java.util.UUID;

public class VpnSubscription {
    private UUID id;
    private UUID vpnProfileId;
    private UUID tariffId;
    private UUID userId;
    private Instant startsAt;
    private Instant expiresAt;
    private String status;  // ACTIVE, EXPIRED, CANCELLED
}
