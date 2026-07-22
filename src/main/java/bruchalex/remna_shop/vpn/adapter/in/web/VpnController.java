package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.vpn.domain.Profile;
import bruchalex.remna_shop.vpn.domain.VpnProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

    private final VpnProviderPort vpnProviderPort;

    @GetMapping("/by-telegram-id/{telegramId}")
    public ResponseEntity<List<Profile>> getVpnProfile(@PathVariable("telegramId") String telegramId) {
        var profile = vpnProviderPort.getVpnProfileByTelegramId(telegramId);
        return ResponseEntity.ok(profile);
    }
}
