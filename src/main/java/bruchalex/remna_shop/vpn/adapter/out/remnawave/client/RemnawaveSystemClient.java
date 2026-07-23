package bruchalex.remna_shop.vpn.adapter.out.remnawave.client;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.RemnawaveMetadataResponse;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.dto.RemnawaveResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/system")
public interface RemnawaveSystemClient {
    
    @GetExchange("/metadata")
    RemnawaveResponse<RemnawaveMetadataResponse> getRemnawaveInformation();
}
