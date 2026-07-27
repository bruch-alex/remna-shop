package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDeviceKey {
    @Column(name = "profile_id")
    private UUID profileId;

    @Column(name = "device_id")
    private Long deviceId;
}
