package bruchalex.remna_shop.vpn_profile.out.remnawave;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "integration.remnawave")
@Validated
public record RemnawaveApiProperties(
        String baseUrl,
        String apiKey
) {}
