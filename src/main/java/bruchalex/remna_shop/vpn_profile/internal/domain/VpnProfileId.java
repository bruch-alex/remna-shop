package bruchalex.remna_shop.vpn_profile.internal.domain;

import java.util.Objects;
import java.util.UUID;

public record VpnProfileId(UUID value) {

    public VpnProfileId {
        Objects.requireNonNull(value, "VpnProfileId value must not be null");
    }

    public VpnProfileId() {
        this(UUID.randomUUID());
    }
}
