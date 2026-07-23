package bruchalex.remna_shop.vpn.application.port.out;

public interface VpnConnectivityPort {
    
    boolean isConnected();

    boolean isAuthenticated();
}
