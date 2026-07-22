package bruchalex.remna_shop.vpn.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "device", schema = "vpn")
public class DeviceJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long deviceId;

    @Column(name = "hwid")
    private String hwid;

    @Column(name = "label")
    private String label;

    @OneToMany(mappedBy = "device")
    private List<DeviceLabelJpaEntity> labels;

    @Column(name = "os")
    private String os; // Android,Windows,ios, etc.

    @Column(name = "model")
    private String model; // Pixel 10, iPhone 17, etc.
}
