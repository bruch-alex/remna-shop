package bruchalex.remna_shop.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, UserId> {
    Optional<MyUser> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
