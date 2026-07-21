package bruchalex.remna_shop.vpn.adapter.out.remnawave;


import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.CreateUserRequest;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.RemnawaveResponse;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface RemnawaveRestClient {

    @PostExchange("/users")
    RemnawaveResponse<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest);
}
