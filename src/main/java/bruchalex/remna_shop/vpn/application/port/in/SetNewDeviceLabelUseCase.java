package bruchalex.remna_shop.vpn.application.port.in;

import java.util.UUID;

public interface SetNewDeviceLabelUseCase {

    Result execute(Command command);

    record Result(String hwid, String label) {
    }

    record Command(UUID userId, String hwid, String newLabel) {
    }
}
