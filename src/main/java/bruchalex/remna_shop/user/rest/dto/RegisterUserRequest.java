package bruchalex.remna_shop.user.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterUserRequest(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("repeat-password") String repeatPassword
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
