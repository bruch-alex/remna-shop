package bruchalex.remna_shop.vpn_profile.out.remnawave;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(RemnawaveApiProperties.class)
public class RemnawaveHttpApiConfig {
    @Bean
    RemnawaveHttpApi remnawaveHttpApi(RemnawaveApiProperties properties) {
        var restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.apiKey())
                .build();

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(RemnawaveHttpApi.class);
    }
}
