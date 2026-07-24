package bruchalex.remna_shop.user.application.port.out.persistence;

import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.MyUser;
import bruchalex.remna_shop.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, UserId> {
    Optional<MyUser> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
