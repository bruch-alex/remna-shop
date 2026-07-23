package bruchalex.remna_shop.vpn.domain;

import java.util.List;

public interface VpnUserManagementPort {
    Profile create(Profile profile);

    List<Profile> getVpnProfileByTelegramId(String telegramId);
}
