package bruchalex.remna_shop.user.infra.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "remnashop.superuser")
public record SuperUserProperties(String username, String email, String password) {
}
