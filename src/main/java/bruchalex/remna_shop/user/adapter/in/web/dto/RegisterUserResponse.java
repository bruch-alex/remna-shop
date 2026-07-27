package bruchalex.remna_shop.user.adapter.in.web.dto;

import bruchalex.remna_shop.user.application.port.in.web.RegisterUserUseCase;

import java.time.Instant;

public record RegisterUserResponse(String email, Instant createdAt) {
    public static RegisterUserResponse of(RegisterUserUseCase.Result result) {
        return new RegisterUserResponse(result.email(), result.createdAt());
    }
}
