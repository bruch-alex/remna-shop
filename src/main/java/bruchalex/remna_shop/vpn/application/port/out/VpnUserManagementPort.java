package bruchalex.remna_shop.vpn.application.port.out;

import bruchalex.remna_shop.vpn.domain.Profile;
import java.util.List;

public interface VpnUserManagementPort {
    Profile create(Profile profile);

    List<Profile> getVpnProfileByTelegramId(String telegramId);
}
