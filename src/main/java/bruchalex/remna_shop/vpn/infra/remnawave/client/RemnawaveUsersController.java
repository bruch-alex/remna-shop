package bruchalex.remna_shop.vpn.infra.remnawave.client;

import bruchalex.remna_shop.vpn.infra.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.DeleteUserResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.RemnawaveResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.UserResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveApiException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.UUID;

@HttpExchange("/users")
public interface RemnawaveUsersController {
    @PostExchange
    RemnawaveResponse<UserResponse> createUser(
            @RequestBody CreateUserRequest createUserRequest
    ) throws RemnawaveApiException;

    @DeleteExchange("/{uuid}")
    RemnawaveResponse<DeleteUserResponse> deleteUser(
            @PathVariable("uuid") UUID uuid
    ) throws RemnawaveApiException;

    @GetExchange("/by-telegram-id/{telegramId}")
    RemnawaveResponse<List<UserResponse>> getUserByTelegramId(
            @PathVariable("telegramId") String telegramId
    ) throws RemnawaveApiException;

    @GetExchange("/by-username/{username}")
    RemnawaveResponse<UserResponse> getUserByUsername(
            @PathVariable("username") String username
    ) throws RemnawaveApiException;

    @GetExchange("/by-email/{email}")
    RemnawaveResponse<List<UserResponse>> getUsersByEmail(
            @PathVariable("email") String email
    ) throws RemnawaveApiException;
}
