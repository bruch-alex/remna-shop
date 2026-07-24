package bruchalex.remna_shop.vpn.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceLabelKey {

    @Column(name = "profile_uuid")
    private UUID profileUuid;

    @Column(name = "device_id")
    private Long deviceId;
}
