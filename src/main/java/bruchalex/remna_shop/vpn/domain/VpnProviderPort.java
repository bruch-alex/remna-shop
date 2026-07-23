package bruchalex.remna_shop.vpn.domain;

import java.util.List;

public interface VpnProviderPort {
    boolean isAuthenticated();

    Profile create(Profile profile);

    List<Profile> getVpnProfileByTelegramId(String telegramId);
}
