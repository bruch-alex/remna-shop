package bruchalex.remna_shop.tariff.domain;

import bruchalex.remna_shop.tariff.application.CreateNewTariffCommand;
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
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Tariff {

    @Id
    private UUID id;
    private String name;
    private boolean trial;
    private boolean enabled;

    private Short trafficLimitGb;
    private Byte devicesLimit;
    private Duration durationDays;
    private Short priceRubles;

    private Instant createdAt;
    private Instant updatedAt;

    public static Tariff create(String name, Short trafficLimitGb, Byte devicesLimit, Short priceRubles) {
        return new Tariff(
                UUID.randomUUID(),
                name,
                false,
                false,
                trafficLimitGb,
                devicesLimit,
                Duration.ofDays(30),
                priceRubles,
                Instant.now(),
                Instant.now()
        );
    }

    public static Tariff fromCommand(CreateNewTariffCommand command) {
        return Tariff.create(
                command.name(),
                command.trafficLimitGb(),
                command.devicesLimit(),
                command.priceRubles()
        );
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
