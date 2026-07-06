package bruchalex.remna_shop.vpn_profile;

import java.util.UUID;

public record ProvisionVpnProfileCommand(
        UUID userUuid
) {
}
