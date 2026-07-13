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
public class MyUser {

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

    public static MyUser create(Email email, HashedPassword hashedPassword, UserRole role) {
        var now = Instant.now();
        return new MyUser(new UserId(), email, hashedPassword, role, now, now);
    }
}
