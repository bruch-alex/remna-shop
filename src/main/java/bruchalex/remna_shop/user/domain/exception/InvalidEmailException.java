package bruchalex.remna_shop.user.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("Invalid email: " + email);
    }
}
