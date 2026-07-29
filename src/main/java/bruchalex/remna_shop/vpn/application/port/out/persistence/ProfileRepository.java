package bruchalex.remna_shop.vpn.application.port.out.persistence;

import bruchalex.remna_shop.vpn.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByIdAndUserId(UUID id, UUID userId);

}
