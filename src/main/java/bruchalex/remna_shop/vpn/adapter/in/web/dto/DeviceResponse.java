package bruchalex.remna_shop.vpn.adapter.in.web.dto;

public record DeviceResponse(
        String label,
        String hwid,
        String model,
        String os
) {
}
