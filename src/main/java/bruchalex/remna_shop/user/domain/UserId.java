package bruchalex.remna_shop.user.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
         Assert.notNull(value, "Id cannot be null");
    }

    public UserId() {
        this(UUID.randomUUID());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }
}
