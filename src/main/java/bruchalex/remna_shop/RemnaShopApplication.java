package bruchalex.remna_shop;

import bruchalex.remna_shop.user.infra.bootstrap.SuperUserProperties;
import bruchalex.remna_shop.shared.auth.JwtProperties;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.RemnawaveProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, SuperUserProperties.class, RemnawaveProperties.class})
public class RemnaShopApplication {

    static void main(String[] args) {
        SpringApplication.run(RemnaShopApplication.class, args);
    }

}
