package bruchalex.remna_shop.tariff.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Boolean isTrial;
    private Boolean isActive;

    private Instant createdAt;
    private Instant updatedAt;
    private Duration duration;
    private Integer price;
    private Integer maxDevicesAllowed;
    private Integer trafficLimitGigabytes;
}
