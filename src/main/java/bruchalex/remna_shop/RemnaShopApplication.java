package bruchalex.remna_shop;

import bruchalex.remna_shop.shared.AppProperties;
import bruchalex.remna_shop.shared.auth.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, AppProperties.class})
public class RemnaShopApplication {

    static void main(String[] args) {
        SpringApplication.run(RemnaShopApplication.class, args);
    }

}
