package bruchalex.remna_shop.vpn.adapter.out.remnawave.client;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.RemnawaveResponse;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.UserResponse;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveApiException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/api/users")
public interface RemnawaveUserClient {

    @PostExchange
    RemnawaveResponse<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws RemnawaveApiException;

    @GetExchange("/by-telegram-id/{telegramId}")
    RemnawaveResponse<List<UserResponse>> getUserByTelegramId(@PathVariable("telegramId") String telegramId) throws RemnawaveApiException;
}
