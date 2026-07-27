package bruchalex.remna_shop.vpn.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "device", schema = "vpn_module")
public class Device {
    @Id
    @Column(name = "id")
    private String id; // hwid

    private String model;

    private String os;

    private String userAgent;

}
