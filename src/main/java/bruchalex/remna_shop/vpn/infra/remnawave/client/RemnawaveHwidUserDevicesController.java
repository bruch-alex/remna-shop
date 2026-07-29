package bruchalex.remna_shop.vpn.infra.remnawave.client;

import bruchalex.remna_shop.vpn.infra.remnawave.dto.DevicesResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.dto.RemnawaveResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.UUID;

@HttpExchange("/hwid")
public interface RemnawaveHwidUserDevicesController {
    @GetExchange("/devices")
    RemnawaveResponse<DevicesResponse> getAllHwidDevices();

    @GetExchange("/devices/{userUuid}")
    RemnawaveResponse<DevicesResponse> getUserHwidDevices(
            @PathVariable("userUuid") UUID userUuid
    );

    @PostExchange("/devices/delete")
    RemnawaveResponse<DevicesResponse> deleteUserHwidDevice(@RequestBody UUID userUuid, @RequestBody String hwid)
            throws RemnawaveApiException;
}
