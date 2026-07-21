package bruchalex.remna_shop.vpn.in.rest;

import java.util.UUID;

public record CreateVpnProfileRequest(
        UUID vpnProfileId,
        UUID userId
) {
}
