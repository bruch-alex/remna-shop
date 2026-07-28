package bruchalex.remna_shop.vpn.domain.exception;

public class VpnProfileNotFoundException extends RuntimeException {
    public VpnProfileNotFoundException() {
        super("VPN profile not found");
    }
}
