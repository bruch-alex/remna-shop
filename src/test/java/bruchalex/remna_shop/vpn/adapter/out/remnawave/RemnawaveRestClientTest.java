package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

class RemnawaveRestClientTest {

    private MockRestServiceServer server;
    private RemnawaveRestClient remnawaveRestClient;

    @BeforeEach
    void setUp() {
        var builder = RestClient.builder()
                .baseUrl("http://remnawave.test/api")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer test-key");
        server = MockRestServiceServer.bindTo(builder).build();
        remnawaveRestClient = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(builder.build()))
                .build()
                .createClient(RemnawaveRestClient.class);
    }

}
