package bruchalex.remna_shop.vpn.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RenameDeviceRequest(
        @JsonProperty("newName")
        @NotBlank
        String newName
) {
}
