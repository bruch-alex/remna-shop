package bruchalex.remna_shop.vpn.infra.remnawave.dto;

import java.time.Instant;

public record Node(Instant connectedAt, String nodeName) {}
