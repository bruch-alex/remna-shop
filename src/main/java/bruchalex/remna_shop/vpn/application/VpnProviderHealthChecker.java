package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.application.port.out.VpnConnectivityPort;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VpnProviderHealthChecker {

    private final VpnConnectivityPort vpnConnectivityPort;
    private final ConfigurableApplicationContext context;
    private final AtomicBoolean lastKnownState = new AtomicBoolean(false);

    @EventListener(ApplicationReadyEvent.class)
    public void checkAuthOnStartup() {
        if (!vpnConnectivityPort.isAuthenticated()) {
            log.error("Cannot authenticate with VPN provider — shutting down");
            int exitCode = SpringApplication.exit(context, () -> 1);
            System.exit(exitCode);
            return;
        }
        log.info("VPN provider is authenticated");
        lastKnownState.set(true);
    }

    @Scheduled(initialDelay = 30_000, fixedDelay = 30_000)
    public void checkConnectionPeriodically() {
        boolean connected = vpnConnectivityPort.isConnected();
        boolean wasConnected = lastKnownState.getAndSet(connected);

        if (connected && !wasConnected) {
            log.info("VPN provider connection recovered");
        } else if (!connected && wasConnected) {
            log.error("Lost connection with VPN provider");
        }
    }
}
