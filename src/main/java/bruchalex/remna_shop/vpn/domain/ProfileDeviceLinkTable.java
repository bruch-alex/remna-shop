package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "profile_device", schema = "vpn_module")
public class ProfileDeviceLinkTable {
    @EmbeddedId
    ProfileDeviceKey profileDeviceKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("deviceId")
    @JoinColumn(name = "device_id")
    Device device;

    @Column(name = "label")
    String label;
}
