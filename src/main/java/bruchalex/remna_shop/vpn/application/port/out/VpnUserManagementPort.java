package bruchalex.remna_shop.vpn.application.port.out;

import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;

import java.util.List;
import java.util.UUID;

public interface VpnUserManagementPort {
    Profile create(Profile profile);

    Profile getProfileByUsername(String username);

    List<Profile> getProfilesByEmail(String email);

    List<Profile> getProfilesByTelegramId(String telegramId);

    List<Device> getDevicesByExternalId(UUID profileId);

    List<Device> removeDevicesByExternalIdAndHwid(UUID profileId, String hwid);
}
