package bruchalex.remna_shop.vpn.domain;

public class VpnProviderException extends RuntimeException {
    public VpnProviderException(String message, Integer status) {
        super("VPN Provider Error: " + message + "Status: " + status);
    }
}
