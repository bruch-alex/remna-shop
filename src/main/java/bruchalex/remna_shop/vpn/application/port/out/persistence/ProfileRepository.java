package bruchalex.remna_shop.vpn.application.port.out.persistence;

import bruchalex.remna_shop.vpn.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByIdAndUserId(UUID id, UUID userId);

    List<Profile> findAllByUserId(UUID userId);

    @Query("""
                select distinct p
                from Profile p
                left join fetch p.deviceLabels
                where p.userId = :userId
            """)
    List<Profile> findAllByUserIdWithDevices(@Param("userId") UUID userId);
}
