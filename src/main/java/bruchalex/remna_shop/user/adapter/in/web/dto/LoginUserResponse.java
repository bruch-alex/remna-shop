package bruchalex.remna_shop.user.adapter.in.web.dto;

import bruchalex.remna_shop.user.application.port.in.web.LoginUserUseCase;

public record LoginUserResponse(String accessToken) {
    public static LoginUserResponse of(LoginUserUseCase.Result result) {
        return new LoginUserResponse(result.accessToken());
    }
}
