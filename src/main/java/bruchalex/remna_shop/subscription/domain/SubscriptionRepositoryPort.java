package bruchalex.remna_shop.subscription.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepositoryPort extends JpaRepository<Subscription, UUID> {
}
