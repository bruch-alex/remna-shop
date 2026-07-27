package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
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

    private Integer deviceLimit;
    private Integer trafficLimitGb;
    private String subscriptionUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant expiresAt;

    private String telegramId;
    private String label;

    @Builder.Default
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileDeviceLinkTable> deviceLabels = new ArrayList<>();

    private Instant fetchedAt;

    public void merge(Profile profile) {
        this.deviceLimit = profile.deviceLimit;
        this.trafficLimitGb = profile.trafficLimitGb;
        this.subscriptionUrl = profile.subscriptionUrl;
        this.createdAt = profile.createdAt;
        this.updatedAt = profile.updatedAt;
        this.expiresAt = profile.expiresAt;
        this.telegramId = profile.telegramId;
        this.label = profile.label;
        this.fetchedAt = Instant.now();
    }

    public void merge(List<Device> devices) {
        Map<String, Device> incomingByHwid = devices.stream()
                .collect(Collectors.toMap(Device::getId, Function.identity()));

        // 1. Remove links whose device no longer exists in the incoming list
        deviceLabels.removeIf(link -> !incomingByHwid.containsKey(link.device.getId()));

        // 2. Track which hwids already have a link, so we know what's new
        Set<String> existingHwids = deviceLabels.stream()
                .map(link -> link.device.getId())
                .collect(Collectors.toSet());

        // 3. Add links for devices that aren't linked yet
        devices.stream()
                .filter(device -> !existingHwids.contains(device.getId()))
                .forEach(device -> {
                    var newLink = ProfileDeviceLinkTable.builder()
                            .profile(this)
                            .device(device)
                            .label("New Device")
                            .build();
                    this.deviceLabels.add(newLink);
                });
    }
}
