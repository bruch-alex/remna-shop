package bruchalex.remna_shop.vpn.adapter.in.web.dto;

public record DeviceResponse(
        String hwid,
        String model,
        String os,
        String label
) {
}
