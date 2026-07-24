package bruchalex.remna_shop.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "user_module")
public class ShopUser {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "uuid"))
    private UserId uuid;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private HashedPassword hashedPassword;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "role"))
    private UserRole role;

    private Instant createdAt;

    private Instant updatedAt;

    public static ShopUser create(Email email, HashedPassword hashedPassword, UserRole role) {
        var now = Instant.now();
        return new ShopUser(new UserId(), email, hashedPassword, role, now, now);
    }
}
