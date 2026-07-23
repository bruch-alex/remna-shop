package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.client.RemnawaveSystemClient;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveApiException;
import bruchalex.remna_shop.vpn.domain.VpnConnectivityPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectivityAdapter implements VpnConnectivityPort {

    private final RemnawaveSystemClient remnawaveSystemClient;

    @Override
    public boolean isAuthenticated() {
        try {
            var info = remnawaveSystemClient.getRemnawaveInformation().response();
            log.debug("Connected to Remnawave Backend version: {}", info.version());
            return true;
        } catch (ResourceAccessException _) {
            log.debug("Remnawave is unreachable. Network error");
            return false;
        } catch (RemnawaveApiException e) {
            log.debug("Not authenticated with Remnawave: status={}", e.getStatus());
            return false;
        }
    }

    @Override
    public boolean isConnected() {
        try {
            remnawaveSystemClient.getRemnawaveInformation();
            return true;
        } catch (ResourceAccessException _) {
            log.debug("Remnawave is unreachable. Network error");
            return false;
        } catch (RemnawaveApiException _) {
            return true;
        }
    }
}
