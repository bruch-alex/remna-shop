package bruchalex.remna_shop.user.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginUserRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 15, max = 128, message = "Password must be between 15 and 128 chars")
        String password
) {
}
