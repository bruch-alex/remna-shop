package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device_label", schema = "vpn_module")
public class DeviceLabel {
    @EmbeddedId
    ProfileDeviceKey profileDeviceKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    Profile profile;

    @Column(name = "label")
    String label;

    public String getDeviceId() {
        return profileDeviceKey.getDeviceId();
    }
}
