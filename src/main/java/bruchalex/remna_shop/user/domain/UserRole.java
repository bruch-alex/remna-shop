package bruchalex.remna_shop.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private String value;

    public static final UserRole USER = new UserRole("USER");
    public static final UserRole ADMIN = new UserRole("ADMIN");

    public static UserRole of(String value) {
        return switch(value) {
            case "ADMIN" -> ADMIN;
            case "USER" -> USER;
            default -> throw new IllegalArgumentException("Unknown role: " + value);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole role = (UserRole) o;
        return Objects.equals(value, role.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
