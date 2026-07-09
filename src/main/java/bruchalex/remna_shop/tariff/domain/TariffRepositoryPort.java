package bruchalex.remna_shop.tariff.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TariffRepositoryPort extends JpaRepository<Tariff, Long> {
    Optional<Tariff> findByTrialAndEnabled(boolean trial, boolean enabled);

    Optional<Tariff> findTariffById(UUID id);
}
