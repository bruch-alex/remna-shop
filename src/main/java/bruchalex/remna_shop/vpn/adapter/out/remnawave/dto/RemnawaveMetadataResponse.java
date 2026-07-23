package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RemnawaveMetadataResponse(
        String version,
        Build build

) {
    public record Build(
            String time,
            String number
    ) {
    }
}
