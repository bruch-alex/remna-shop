package bruchalex.remna_shop.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<MyUser, UserId> {
    Optional<MyUser> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
