package bruchalex.remna_shop.user.rest.dto;

import bruchalex.remna_shop.user.domain.MyUser;

import java.time.Instant;

public record RegisterUserResponse(
        String email,
        Instant createdAt
) {
    public static RegisterUserResponse from(MyUser user) {
        return new RegisterUserResponse(
                user.getEmail().value(),
                user.getCreatedAt()
        );
    }
}
