package bruchalex.remna_shop.vpn.domain.exception;

public class VpnProviderException extends RuntimeException {
    public VpnProviderException(String message, Integer status) {
        super("VPN Provider Error: " + message + "Status: " + status);
    }
}
