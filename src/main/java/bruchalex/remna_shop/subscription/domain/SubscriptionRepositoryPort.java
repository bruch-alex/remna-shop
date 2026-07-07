package bruchalex.remna_shop.subscription.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SubscriptionRepositoryPort extends JpaRepository<Subscription, Integer> {
    boolean existsSubscriptionByUserUuid(UUID userUuid);

    boolean existsSubscriptionByUserUuidAndType(UUID userUuid, SubscriptionType type);


    @Modifying
    @Query("update Subscription s set s.paused = true where s.userUuid = :userUuid")
    int pauseSubscriptionByUserUuid(UUID userUuid);}
