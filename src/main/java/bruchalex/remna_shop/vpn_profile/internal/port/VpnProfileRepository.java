package bruchalex.remna_shop.vpn_profile.internal.port;

import bruchalex.remna_shop.vpn_profile.internal.domain.VpnProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VpnProfileRepository extends JpaRepository<VpnProfile, Integer> {
    List<VpnProfile> findByUserEmail(String userEmail);
    List<VpnProfile> findByUserId(UUID userId);
    Optional<VpnProfile> findByTelegramId(String telegramId);
}
