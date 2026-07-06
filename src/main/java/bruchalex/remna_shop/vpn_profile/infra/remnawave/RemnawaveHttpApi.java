package bruchalex.remna_shop.vpn_profile.infra.remnawave;

import bruchalex.remna_shop.vpn_profile.infra.remnawave.dto.CreateRemnawaveUserRequest;
import bruchalex.remna_shop.vpn_profile.infra.remnawave.dto.RemnawaveUserResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface RemnawaveHttpApi {
    @PostExchange("/users")
    RemnawaveUserResponse createUser(@RequestBody CreateRemnawaveUserRequest user);
}
