package bruchalex.remna_shop.vpn.infra.remnawave.client;

import bruchalex.remna_shop.vpn.infra.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.RemnawaveResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.UserResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveApiException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/users")
public interface RemnawaveUserClient {
    @PostExchange
    RemnawaveResponse<UserResponse> createUser(
        @RequestBody CreateUserRequest createUserRequest
    ) throws RemnawaveApiException;

    @GetExchange("/by-telegram-id/{telegramId}")
    RemnawaveResponse<List<UserResponse>> getUserByTelegramId(
        @PathVariable("telegramId") String telegramId
    ) throws RemnawaveApiException;
}
