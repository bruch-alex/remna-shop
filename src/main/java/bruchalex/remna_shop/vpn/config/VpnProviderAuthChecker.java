package bruchalex.remna_shop.vpn.config;

import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VpnProviderAuthChecker {

    private final VpnProviderPort vpnProviderPort;

    @EventListener(ApplicationReadyEvent.class)
    public void checkAuthOnStartup() {
        if (!vpnProviderPort.isAuthenticated()) {
            throw new IllegalStateException("Cannot authenticate with VPN provider — aborting startup");
        }
        log.info("Connection to VPN provider established");
    }

    @Scheduled(fixedDelay = 15_000)
    public void checkAuthPeriodically() {
        boolean authenticated = vpnProviderPort.isAuthenticated();
        if (!authenticated) {
            log.error("Lost authentication with VPN provider");
        }
    }
}
