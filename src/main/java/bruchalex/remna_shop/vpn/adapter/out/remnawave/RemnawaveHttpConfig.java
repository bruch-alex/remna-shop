package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.nio.charset.StandardCharsets;

@Configuration
public class RemnawaveHttpConfig {
    @Bean
    RemnawaveRestClient remnawaveRestClient(RemnawaveProperties properties) {
        var restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.apiKey())
                .requestFactory(new BufferingClientHttpRequestFactory(
                        new SimpleClientHttpRequestFactory()))
                .requestInterceptor((request, body, execution) -> {
                    System.out.println(">>> " + request.getMethod() + " " + request.getURI());
                    System.out.println(">>> " + new String(body, StandardCharsets.UTF_8));
                    var response = execution.execute(request, body);
                    System.out.println("<<< " + response.getStatusCode());
                    System.out.println("<<< " + new String(
                            response.getBody().readAllBytes(), StandardCharsets.UTF_8));
                    return response;
                })
                .build();

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(RemnawaveRestClient.class);
    }
}