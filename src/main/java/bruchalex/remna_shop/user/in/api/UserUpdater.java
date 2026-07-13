package bruchalex.remna_shop.user.in.api;

import java.util.UUID;

public interface UserUpdater {
    void assignVpnProfile(UUID userUuid, UUID vpnProfileUuid);
}
