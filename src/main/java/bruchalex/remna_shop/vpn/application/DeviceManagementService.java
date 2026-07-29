package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.application.port.in.DeviceManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.out.VpnUserManagementPort;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import bruchalex.remna_shop.vpn.domain.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceManagementService implements DeviceManagementUseCase {

    private final ProfileRepository profileRepository;
    private final VpnUserManagementPort vpnUserManagementPort;

    @Override
    @Transactional
    public DeviceResult renameDevice(RenameDeviceCommand command) {
        log.debug("Rename device command: {}", command);
        var profile = profileRepository.findByIdAndUserId(command.profileId(), command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        var renamed = profile.renameDevice(command.hwid(), command.newLabel());
        log.debug("Renamed device: {}", renamed);
        profileRepository.save(profile);
        return new DeviceResult(renamed.getDeviceId(), renamed.getLabel());
    }

    @Override
    @Transactional
    public void removeDevice(RemoveDeviceCommand command) {
        var profile = profileRepository.findByIdAndUserId(command.profileId(), command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        profile.removeDevice(command.hwid());
        vpnUserManagementPort.removeDevicesByExternalIdAndHwid(profile.getRemnawaveUserUuid(), command.hwid());
        profileRepository.save(profile);
    }
}
