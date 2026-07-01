package bruchalex.remna_shop.user.application.dto;

public record LoginUserRequest(
        String email,
        String password
) {
}
