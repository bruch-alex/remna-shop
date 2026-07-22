package bruchalex.remna_shop.vpn.domain;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.AuthResponse;

import java.util.List;

public interface VpnProviderPort {
    AuthResponse getAuthStatus();

    Profile create(Profile profile);

    List<Profile> getVpnProfileByTelegramId(String telegramId);
}
