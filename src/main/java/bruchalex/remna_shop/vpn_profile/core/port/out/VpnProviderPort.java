package bruchalex.remna_shop.vpn_profile.core.port.out;

import bruchalex.remna_shop.vpn_profile.core.domain.VpnProfile;

import java.time.Instant;

public interface VpnProviderPort {
    VpnProfile createVpnProfile(String username, Instant expirationDate);

}
