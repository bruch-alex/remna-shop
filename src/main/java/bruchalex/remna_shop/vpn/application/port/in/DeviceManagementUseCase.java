package bruchalex.remna_shop.vpn.application.port.in;

import java.util.UUID;

public interface DeviceManagementUseCase {

    DeviceResult renameDevice(RenameDeviceCommand command);

    void removeDevice(RemoveDeviceCommand command);

    record DeviceResult(String hwid, String label) {
    }

    record RenameDeviceCommand(UUID userId, UUID profileId, String hwid, String newLabel) {
    }

    record RemoveDeviceCommand(UUID userId, UUID profileId, String hwid) {
    }
}
