package bruchalex.remna_shop.tariff.domain;

import bruchalex.remna_shop.tariff.application.CreateNewTariffCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Integer trafficLimitGb;
    private Integer devicesLimit;
    private Integer durationDays;
    private Integer priceRubles;

    private Instant createdAt;
    private Instant updatedAt;

    public static Tariff create(String name, Integer trafficLimitGb, Integer devicesLimit, Integer priceRubles, Integer durationDays) {
        var now =  Instant.now();
        return new Tariff(
                UUID.randomUUID(),
                name,
                false,
                false,
                trafficLimitGb,
                devicesLimit,
                durationDays,
                priceRubles,
                now,
                now
        );
    }

    public static Tariff fromCommand(CreateNewTariffCommand command) {

        return Tariff.create(
                command.name(),
                command.trafficLimitGb(),
                command.devicesLimit(),
                command.priceRubles(),
                command.durationDays()
        );
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void setNewPrice(Integer newPriceRubles) {
        if (newPriceRubles <= 0){
            throw new IllegalArgumentException();
        }
        this.priceRubles = newPriceRubles;
    }
}
