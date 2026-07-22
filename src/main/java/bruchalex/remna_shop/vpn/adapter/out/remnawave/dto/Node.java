package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import java.time.Instant;

public record Node(
        Instant connectedAt,
        String nodeName
) {
}
