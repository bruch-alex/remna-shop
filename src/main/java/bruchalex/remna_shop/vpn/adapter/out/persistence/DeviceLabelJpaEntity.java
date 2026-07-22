package bruchalex.remna_shop.vpn.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "device_label", schema = "vpn")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceLabelJpaEntity {
    @EmbeddedId
    DeviceLabelKey deviceLabelKey;

    @ManyToOne
    @MapsId("profileUuid")
    @JoinColumn(name = "profile_uuid")
    ProfileJpaEntity profile;

    @ManyToOne
    @MapsId("deviceId")
    @JoinColumn(name = "device_id")
    DeviceJpaEntity device;

    String label;
}
