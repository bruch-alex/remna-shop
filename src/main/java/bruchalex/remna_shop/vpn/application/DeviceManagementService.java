package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.application.port.in.DeviceManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.out.VpnUserManagementPort;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceManagementService implements DeviceManagementUseCase {

    private final ProfileRepository profileRepository;
    private final VpnUserManagementPort vpnUserManagementPort;

    @Override
    public Result setNewDeviceLabel(Command command) {
        var profile = profileRepository.findById(command.userId()).orElseThrow();
        var renamed = profile.renameDevice(command.hwid(), command.newLabel());
        profileRepository.save(profile);
        return new Result(renamed.getDeviceId(), renamed.getLabel());
    }

    @Override
    @Transactional
    public void removeDevice(UUID profileId, UUID authUserUuid, String deviceId) {
        var profile = profileRepository.findById(profileId).orElseThrow();

        if (!profile.getUserId().equals(authUserUuid)) {
            throw new AccessDeniedException("You are not allowed to remove this device");
        }

        profile.removeDevice(deviceId);
        vpnUserManagementPort.removeDevicesByExternalIdAndHwid(profile.getRemnawaveUserUuid(), deviceId);
        profileRepository.save(profile);
    }
}
