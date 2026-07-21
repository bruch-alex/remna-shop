package bruchalex.remna_shop.vpn.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VpnProfileRepository extends JpaRepository<VpnProfile, UUID> {
}
