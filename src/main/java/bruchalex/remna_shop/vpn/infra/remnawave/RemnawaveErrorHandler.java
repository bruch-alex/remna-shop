package bruchalex.remna_shop.vpn.infra.remnawave;

import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveClientException;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveErrorResponse;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveResponseParseException;
import bruchalex.remna_shop.vpn.infra.remnawave.exception.RemnawaveServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class RemnawaveErrorHandler {

    private final ObjectMapper objectMapper;

    public void handle(HttpRequest request, ClientHttpResponse response) throws IOException {
        HttpStatusCode status = response.getStatusCode();
        byte[] rawBody = StreamUtils.copyToByteArray(response.getBody());
        String raw = new String(rawBody, StandardCharsets.UTF_8);

        RemnawaveErrorResponse errorBody = tryParse(raw);

        if (errorBody != null) {
            log.warn("Remnawave API error: {} (status={})", errorBody.getMessage(), status);
            log.error("Request path: {}", request.getURI().getPath());
        } else {
            log.error("Remnawave API returned unparseable error body (status={}): {}", status, raw);
            log.error("Request path: {}", request.getURI().getPath());
            throw new RemnawaveResponseParseException(status.toString(), raw);
        }

        if (status.is4xxClientError()) {
            throw new RemnawaveClientException(status.toString(), errorBody);
        } else {
            throw new RemnawaveServerException(status.toString(), errorBody);
        }
    }

    private RemnawaveErrorResponse tryParse(String raw) {
        return objectMapper.readValue(raw, RemnawaveErrorResponse.class);
    }
}
