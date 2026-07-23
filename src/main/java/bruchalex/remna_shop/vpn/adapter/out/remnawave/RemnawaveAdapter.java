package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveApiException;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.VpnProviderException;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemnawaveAdapter implements VpnProviderPort {

    private final RemnawaveRestClient remnawaveRestClient;
    private final RemnawaveMapper remnawaveMapper;

    @Override
    public boolean isAuthenticated() {
        try {
            var info = remnawaveRestClient.getRemnawaveInformation().response();
            log.debug("Connected to Remnawave Backend version: {}", info.version());
            return info.version() != null;
        } catch (ResourceAccessException _) {
            log.debug("Remnawave is unreachable. Network error");
            return false;
        } catch (RemnawaveApiException _) {
            return false;
        }
    }

    @Override
    public Profile create(Profile profile) {
        var request = CreateUserRequest.builder().username(profile.getUuid()).expireAt(profile.getExpiresAt()).build();
        try {
            var response = remnawaveRestClient.createUser(request).response();
            return remnawaveMapper.toVpnProfile(response);
        } catch (RemnawaveApiException _) {
            throw new VpnProviderException();
        }
    }

    @Override
    public List<Profile> getVpnProfileByTelegramId(String telegramId) {
        try {
            return remnawaveRestClient.getUserByTelegramId(telegramId).response().stream().map(remnawaveMapper::toVpnProfile).toList();
        } catch (RemnawaveApiException e) {
            throw new VpnProviderException();
        }
    }
}
