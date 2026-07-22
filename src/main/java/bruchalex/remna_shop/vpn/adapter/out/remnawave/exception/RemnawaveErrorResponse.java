package bruchalex.remna_shop.vpn.adapter.out.remnawave.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemnawaveErrorResponse {
    private String message;
    private Instant timestamp;
    private HttpStatus status;
    private List<RemnawaveFieldError> errors;
    private String errorCode;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RemnawaveFieldError(
            UUID validation,
            String invalidString,
            String invalidUuid,
            List<String> path
    ) {
    }
}
