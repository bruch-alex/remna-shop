package bruchalex.remna_shop.tariff.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TariffRepositoryPort extends JpaRepository<Tariff, UUID> {
    Optional<Tariff> findByTrialAndEnabled(boolean trial, boolean enabled);

    Optional<Tariff> findTariffById(UUID id);

    List<Tariff> findAllByEnabled(boolean active);

    @Modifying
    @Query("""
           update Tariff t 
           set t.enabled = false 
           where t.id = :id
           """)
    void disableTariff(UUID id);
}