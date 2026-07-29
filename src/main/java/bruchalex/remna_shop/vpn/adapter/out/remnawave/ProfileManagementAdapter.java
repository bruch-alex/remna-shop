package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.application.port.out.VpnUserManagementPort;
import bruchalex.remna_shop.vpn.domain.Device;
import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.VpnProviderException;
import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveHwidUserDevicesController;
import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveUsersController;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveApiException;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveClientException;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileManagementAdapter implements VpnUserManagementPort {

    private final RemnawaveUsersController remnawaveUsersController;
    private final RemnawaveHwidUserDevicesController remnawaveHwidUserDevicesController;
    private final RemnawaveMapper remnawaveMapper;

    @Override
    public Profile create(Profile profile) {
        var request = CreateUserRequest.builder()
                .username(profile.getId())
                .expireAt(profile.getExpiresAt())
                .build();
        try {
            var response = remnawaveUsersController.createUser(request).response();
            return remnawaveMapper.toVpnProfile(response);
        } catch (RemnawaveApiException e) {
            throw new VpnProviderException(e.getMessage(), e.getStatus().value());
        }
    }

    @Override
    public List<Profile> getProfilesByTelegramId(String telegramId) {
        try {
            return remnawaveUsersController
                    .getUserByTelegramId(telegramId)
                    .response()
                    .stream()
                    .map(remnawaveMapper::toVpnProfile)
                    .toList();
        } catch (RemnawaveApiException e) {
            throw new VpnProviderException(e.getMessage(), e.getStatus().value());
        }
    }

    @Override
    public Profile getProfileById(UUID profileId) {
        try {
            var profileResponse = remnawaveUsersController
                    .getUserByUsername(profileId.toString())
                    .response();
            return remnawaveMapper.toVpnProfile(profileResponse);
        } catch (RemnawaveClientException e) {
            throw new RuntimeException("Invalid request to Remnawave", e);
        } catch (RemnawaveServerException | ResourceAccessException e) {
            throw new RuntimeException("Remnawave is unavailable", e);
        }
    }

    @Override
    public List<Profile> getProfilesByEmail(String email) {
        try {
            return remnawaveUsersController
                    .getUsersByEmail(email)
                    .response()
                    .stream()
                    .map(remnawaveMapper::toVpnProfile)
                    .toList();
        } catch (RemnawaveClientException e) {
            throw new RuntimeException("Invalid request to Remnawave", e);
        } catch (RemnawaveServerException | ResourceAccessException e) {
            throw new RuntimeException("Remnawave is unavailable", e);
        }
    }

    /// @param externalId in case of remnawave `RemnawaveUserUuid` should be used
    /// @return list of devices
    @Override
    public List<Device> getDevicesByExternalId(UUID externalId) {
        try {
            return remnawaveHwidUserDevicesController
                    .getUserHwidDevices(externalId)
                    .response()
                    .devices()
                    .stream()
                    .map(remnawaveMapper::toDevice)
                    .toList();
        } catch (RemnawaveClientException e) {
            throw new RuntimeException("Invalid request to Remnawave", e);
        } catch (RemnawaveServerException | ResourceAccessException e) {
            throw new RuntimeException("Remnawave is unavailable", e);
        }
    }

    /// @param externalId in case of remnawave `RemnawaveUserUuid` should be used
    /// @param hwid       hwid of device to delete
    /// @return list of remaining devices after deletion
    @Override
    public List<Device> removeDevicesByExternalIdAndHwid(UUID externalId, String hwid) {
        try {
            return remnawaveHwidUserDevicesController
                    .deleteUserHwidDevice(externalId, hwid)
                    .response()
                    .devices()
                    .stream()
                    .map(remnawaveMapper::toDevice)
                    .toList();
        } catch (RemnawaveClientException e) {
            throw new RuntimeException("Invalid request to Remnawave", e);
        } catch (RemnawaveServerException | ResourceAccessException e) {
            throw new RuntimeException("Remnawave is unavailable", e);
        }
    }
}
