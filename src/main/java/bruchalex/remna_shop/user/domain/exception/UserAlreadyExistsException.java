package bruchalex.remna_shop.user.domain.exception;

import bruchalex.remna_shop.user.domain.Email;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Email email) {
        super("User already exists: " + email.value());
    }
}
