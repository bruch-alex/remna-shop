package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Getter
@ToString
@Entity
@Table(name = "profile", schema = "vpn_module")
public class Profile {

    @Id
    private UUID id;
    private UUID userId;

    private String telegramId;
    private String email;
    private String label;

    private Integer deviceLimit;
    private Integer trafficLimitGb;
    private String subscriptionUrl;
    private Instant expiresAt;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant fetchedAt;

    private UUID remnawaveUserUuid;

    @Builder.Default
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceLabel> deviceLabels = new ArrayList<>();


    public void merge(Profile profile) {
        this.deviceLimit = profile.deviceLimit;
        this.trafficLimitGb = profile.trafficLimitGb;
        this.subscriptionUrl = profile.subscriptionUrl;
        this.createdAt = profile.createdAt;
        this.updatedAt = profile.updatedAt;
        this.expiresAt = profile.expiresAt;
        this.telegramId = profile.telegramId;
        this.email = profile.email;
        this.fetchedAt = Instant.now();
    }

    public void syncDevices(Map<String, Device> devicesByHwid) {
        // 1. Remove labels whose device no longer exists in the incoming list
        deviceLabels.removeIf(link -> !devicesByHwid.containsKey(link.getDeviceId()));

        // 2. Track which hwids already have a label
        Set<String> existingHwids = deviceLabels.stream()
                .map(DeviceLabel::getDeviceId)
                .collect(Collectors.toSet());

        // 3. Add labels for devices that aren't linked yet
        devicesByHwid.entrySet().stream()
                .filter(device -> !existingHwids.contains(device.getKey()))
                .forEach(device -> {
                    var newLink = DeviceLabel.builder()
                            .profileDeviceKey(new ProfileDeviceKey(this.id, device.getKey()))
                            .profile(this)
                            .label("New Device")
                            .build();
                    this.deviceLabels.add(newLink);
                });
    }

    public DeviceLabel renameDevice(String hwid, String newLabel) {
        for (DeviceLabel deviceLabel : this.deviceLabels) {
            if (deviceLabel.getDeviceId().equals(hwid)) {
                deviceLabel.label = newLabel;
                return deviceLabel;
            }
        }
        throw new RuntimeException("No such device with id " + hwid);
    }

    public void setNewUserId(UUID newUserId) {
        if (this.userId != null) {
            throw new IllegalStateException("User already set");
        }
        this.userId = newUserId;
    }

    public void removeDevice(String hwid) {
        this.deviceLabels.removeIf(l -> l.getDeviceId().equals(hwid));
    }
}
