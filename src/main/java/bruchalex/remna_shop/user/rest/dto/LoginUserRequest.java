package bruchalex.remna_shop.user.rest.dto;

public record LoginUserRequest(
        String email,
        String password
) {
}
