package bruchalex.remna_shop.user.domain;

import bruchalex.remna_shop.user.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {
        if (value == null || !isValid(value)) {
            throw new InvalidEmailException(value);
        }
        value = value.trim().toLowerCase();
    }

    private static boolean isValid(String raw) {
        return raw.contains("@") && raw.contains(".");
    }
}
