package bruchalex.remna_shop.vpn.application.port.in;

import java.util.UUID;

public interface DeviceManagementUseCase {

    Result setNewDeviceLabel(Command command);

    void removeDevice(UUID profileId, UUID authUserUuid, String deviceId);

    record Result(String hwid, String label) {
    }

    record Command(UUID userId, String hwid, String newLabel) {
    }
}
