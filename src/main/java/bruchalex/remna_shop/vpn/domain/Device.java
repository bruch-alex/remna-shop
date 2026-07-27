package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device", schema = "vpn_module")
public class Device {
    @Id
    @Column(name = "id")
    private Long id;

    private String hwid;

    private UUID user_id;

    private String model;

    private String os;
}
