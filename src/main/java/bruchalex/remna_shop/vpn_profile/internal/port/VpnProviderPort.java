package bruchalex.remna_shop.vpn_profile.internal.port;

import bruchalex.remna_shop.vpn_profile.internal.domain.VpnProfile;

import java.time.Instant;
import java.util.UUID;

public interface VpnProviderPort {
    VpnProfile createEmptyVpnProfile();

}
