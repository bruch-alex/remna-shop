package bruchalex.remna_shop.tariff.domain;

import bruchalex.remna_shop.tariff.TariffDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TariffRepositoryAdapter extends JpaRepository<Tariff, Integer> {

    @Query("""
            select new bruchalex.remna_shop.tariff.TariffDetails(
                        t.id,
                        t.name,
                        t.maxDevicesAllowed,
                        t.trafficLimitGigabytes,
                        t.duration,
                        t.price)
            from Tariff t
            where t.isTrial = true
            and t.isActive = true
                                  
            """)
    <T> T getCurrentTrialTariff(Class<T> clazz);

    @Modifying
    @Query("""
            select new bruchalex.remna_shop.tariff.TariffDetails(
                        t.id,
                        t.name,
                        t.maxDevicesAllowed,
                        t.trafficLimitGigabytes,
                        t.duration,
                        t.price)
            from Tariff t
            where t.isActive = true
            """)
    List<TariffDetails> getAllActiveTariffs();
}
