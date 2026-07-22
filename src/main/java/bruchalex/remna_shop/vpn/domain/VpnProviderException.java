package bruchalex.remna_shop.vpn.domain;

public class VpnProviderException extends RuntimeException {
    public VpnProviderException() {
        super("Error during vpn-profile creation");
    }
}
