package bruchalex.remna_shop.user.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @JsonProperty("email")
        @NotBlank
        @Email
        String email,

        @JsonProperty("password")
        @NotBlank
        @Size(min = 15, max = 128, message = "Password must be between 15 and 128 chars")
        String password,

        @JsonProperty("repeat-password")
        String repeatPassword
) {
    public RegisterUserRequest {
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }
}
