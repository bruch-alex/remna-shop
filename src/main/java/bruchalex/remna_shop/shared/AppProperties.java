package bruchalex.remna_shop.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.superuser")
public record AppProperties(String username, String email, String password) {
}
