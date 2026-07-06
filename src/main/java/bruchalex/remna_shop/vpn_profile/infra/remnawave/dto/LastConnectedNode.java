package bruchalex.remna_shop.vpn_profile.infra.remnawave.dto;


import java.time.Instant;

public record LastConnectedNode(
        Instant connectedAt,
        String nodeName
) {
}
