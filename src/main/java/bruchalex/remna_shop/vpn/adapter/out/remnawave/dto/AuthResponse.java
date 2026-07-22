package bruchalex.remna_shop.vpn.adapter.out.remnawave.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record AuthResponse(
        @JsonProperty("isLoginAllowed") boolean isLoginAllowed,
        @JsonProperty("isRegisterAllowed") boolean isRegisterAllowed,
        @JsonProperty("authentication") Authentication authentication,
        @JsonProperty("branding") Branding branding

) {
    public record Authentication(
            @JsonProperty("passkey") Passkey passkey,
            @JsonProperty("oauth2") OAuth2 oauth2,
            @JsonProperty("password") Password password
    ) {
    }

    public record Passkey(@JsonProperty("enabled") boolean enabled) {
    }

    public record Password(@JsonProperty("enabled") boolean enabled) {
    }

    public record OAuth2(
            @JsonProperty("providers") Map<String, Boolean> providers
    ) {
    }

    public record Branding(
            @JsonProperty("title") String title,
            @JsonProperty("logoUrl") String logoUrl
    ) {
    }
}
