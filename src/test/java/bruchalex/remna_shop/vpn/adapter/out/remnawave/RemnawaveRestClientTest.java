package bruchalex.remna_shop.vpn.adapter.out.remnawave;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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

    @Test
    void parsesAuthStatus() {
        server.expect(requestTo("http://remnawave.test/api/auth/status"))
                .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer test-key"))
                .andRespond(withSuccess("""
                        {"response":{"isLoginAllowed":true,"isRegisterAllowed":false,
                        "authentication":{"passkey":{"enabled":true},
                        "oauth2":{"providers":{"github":true}},
                        "password":{"enabled":true}},
                        "branding":{"title":null,"logoUrl":null}}}
                        """, MediaType.APPLICATION_JSON));

        var body = remnawaveRestClient.getAuthStatus().response();

        assertThat(body.isRegisterAllowed()).isFalse();
        assertThat(body.authentication().oauth2().providers()).containsEntry("github", true);
        server.verify();
    }

}
