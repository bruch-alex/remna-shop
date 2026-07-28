package bruchalex.remna_shop.vpn.domain;

import lombok.Data;

@Data
public class Device {
    private String id;

    private String model;
    private String os;
    private String userAgent;
}
