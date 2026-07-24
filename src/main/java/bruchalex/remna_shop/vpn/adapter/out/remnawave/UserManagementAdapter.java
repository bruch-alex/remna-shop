package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.application.port.out.VpnUserManagementPort;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.VpnProviderException;
import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveUserClient;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveApiException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManagementAdapter implements VpnUserManagementPort {

    private final RemnawaveUserClient remnawaveUserClient;
    private final RemnawaveMapper remnawaveMapper;

    @Override
    public Profile create(Profile profile) {
        var request = CreateUserRequest.builder()
            .username(profile.getUuid())
            .expireAt(profile.getExpiresAt())
            .build();
        try {
            var response = remnawaveUserClient.createUser(request).response();
            return remnawaveMapper.toVpnProfile(response);
        } catch (RemnawaveApiException _) {
            throw new VpnProviderException();
        }
    }

    @Override
    public List<Profile> getVpnProfileByTelegramId(String telegramId) {
        try {
            return remnawaveUserClient
                .getUserByTelegramId(telegramId)
                .response()
                .stream()
                .map(remnawaveMapper::toVpnProfile)
                .toList();
        } catch (RemnawaveApiException e) {
            throw new VpnProviderException();
        }
    }
}
