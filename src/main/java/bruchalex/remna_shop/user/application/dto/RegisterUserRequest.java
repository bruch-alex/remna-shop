package bruchalex.remna_shop.user.application.dto;

public record RegisterUserRequest(
        String email,
        String password,
        String repeatPassword
) {
    public RegisterUserRequest {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }
}
