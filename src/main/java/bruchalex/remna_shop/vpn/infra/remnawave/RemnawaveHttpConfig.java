package bruchalex.remna_shop.vpn.infra.remnawave;

import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveHwidUserDevicesController;
import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveSystemClient;
import bruchalex.remna_shop.vpn.infra.remnawave.client.RemnawaveUsersController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.Executor;

@Configuration
@EnableConfigurationProperties(RemnawaveProperties.class)
@RequiredArgsConstructor
@Slf4j
public class RemnawaveHttpConfig {

    private final ObjectMapper objectMapper;
    private final RemnawaveErrorHandler errorHandler;

    @Bean
    public Executor remnawaveApiExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("remnawave-api-");
        executor.initialize();
        return executor;
    }

    @Bean
    RestClient restClient(RemnawaveProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.apiKey())
                .defaultStatusHandler(HttpStatusCode::isError, errorHandler::handle)
                .build();
    }

    @Bean
    public HttpServiceProxyFactory remnawaveProxyFactory(
            RestClient remnawaveRestClient
    ) {
        return HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(remnawaveRestClient)
        ).build();
    }

    @Bean
    public RemnawaveUsersController remnawaveUserClient(
            HttpServiceProxyFactory factory
    ) {
        return factory.createClient(RemnawaveUsersController.class);
    }

    @Bean
    public RemnawaveSystemClient remnawaveSystemClient(
            HttpServiceProxyFactory factory
    ) {
        return factory.createClient(RemnawaveSystemClient.class);
    }

    @Bean
    public RemnawaveHwidUserDevicesController remnawaveHwidUserDevicesController(
            HttpServiceProxyFactory factory
    ) {
        return factory.createClient(RemnawaveHwidUserDevicesController.class);
    }
}
