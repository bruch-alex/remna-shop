package bruchalex.remna_shop.vpn.infra.remnawave;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "remnashop.integration.remnawave")
public record RemnawaveProperties(String baseUrl, String apiKey) {}
