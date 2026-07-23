package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import bruchalex.remna_shop.vpn.adapter.out.remnawave.client.RemnawaveSystemClient;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.client.RemnawaveUserClient;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveApiException;
import bruchalex.remna_shop.vpn.adapter.out.remnawave.exception.RemnawaveErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableConfigurationProperties(RemnawaveProperties.class)
@RequiredArgsConstructor
public class RemnawaveHttpConfig {

    private final ObjectMapper objectMapper;

    @Bean
    RestClient restClient(RemnawaveProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.apiKey())
                .defaultStatusHandler(HttpStatusCode::isError, (_, res) -> {
                    var raw = new String(res.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    var error = objectMapper.readValue(raw, RemnawaveErrorResponse.class);
                    throw new RemnawaveApiException(error);
                })
                .build();
    }

    @Bean
    public HttpServiceProxyFactory remnawaveProxyFactory(RestClient remnawaveRestClient) {
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(remnawaveRestClient))
                .build();
    }

    @Bean
    public RemnawaveUserClient remnawaveUserClient(HttpServiceProxyFactory factory) {
        return factory.createClient(RemnawaveUserClient.class);
    }

    @Bean
    public RemnawaveSystemClient remnawaveSystemClient(HttpServiceProxyFactory factory) {
        return factory.createClient(RemnawaveSystemClient.class);
    }
}
