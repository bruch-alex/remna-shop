package bruchalex.remna_shop.vpn.application.port.in;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ProfileManagementUseCase {

    ProfileResult getProfileSummary(GetProfileSummaryCommand command);

    List<ProfileResult> syncRemoteProfiles(SyncProfileCommand command);

    List<ProfileResult> getProfiles(GetProfilesCommand command);

    ProfileResult renameProfile(RenameProfileCommand command);

    record DeviceResult(
            String label,
            String hwid,
            String os,
            String model
    ) {
    }

    record ProfileResult(
            String id,
            String userId,
            String telegramId,
            String label,
            String subscriptionUrl,

            Integer deviceLimit,
            Integer trafficLimitGb,
            Instant expiresAt,

            Integer addedDevices,
            List<DeviceResult> devices
    ) {
    }

    record GetProfileSummaryCommand(UUID userId, UUID profileId) {
    }

    record SyncProfileCommand(UUID userId, String email) {
    }

    record GetProfilesCommand(UUID userId) {
    }

    record RenameProfileCommand(UUID userId, UUID profileId, String newName) {
    }
}
