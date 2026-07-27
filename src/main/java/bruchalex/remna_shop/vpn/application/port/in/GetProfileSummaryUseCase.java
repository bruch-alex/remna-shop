package bruchalex.remna_shop.vpn.application.port.in;

import java.util.List;
import java.util.UUID;

public interface GetProfileSummaryUseCase {

    ProfileResult execute(UUID profileId);

    record DeviceResult(String hwid, String os, String model, String label) {
    }

    record ProfileResult(String id, Integer deviceLimit, List<DeviceResult> devices) {
    }
}
