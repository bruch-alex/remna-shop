package bruchalex.remna_shop.vpn.infra.remnawave.client;

import bruchalex.remna_shop.vpn.infra.remnawave.dto.DevicesResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.RemnawaveResponse;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/hwid")
public interface UserDeviceClient {
    @GetExchange("/devices")
    RemnawaveResponse<DevicesResponse> getAllHwidDevices();

    @GetExchange("/devices/{userUuid}")
    RemnawaveResponse<DevicesResponse> getUserHwidDevices(
        @PathVariable("userUuid") UUID userUuid
    );
}
