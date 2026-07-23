package bruchalex.remna_shop.vpn.domain;

import java.util.List;

public interface VpnProviderPort {
    boolean isAuthenticated();

    boolean isConnected();

    Profile create(Profile profile);

    List<Profile> getVpnProfileByTelegramId(String telegramId);
}
