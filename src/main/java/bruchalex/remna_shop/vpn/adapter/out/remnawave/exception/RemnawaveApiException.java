package bruchalex.remna_shop.vpn.adapter.out.remnawave.exception;

import bruchalex.remna_shop.shared.exception.FieldError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;


@Getter
public class RemnawaveApiException extends RuntimeException {
    private final String message;
    private final HttpStatus status;
    private final List<FieldError> errors;

    public RemnawaveApiException(String message, HttpStatus status, List<FieldError> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public RemnawaveApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.errors = Collections.emptyList();
    }

    public RemnawaveApiException(RemnawaveErrorResponse errorResponse) {
        this.status = errorResponse.getStatus();
        this.message = errorResponse.getMessage();

        List<RemnawaveErrorResponse.RemnawaveFieldError> rawErrors = errorResponse.getErrors();

        this.errors = (rawErrors != null)
                ? rawErrors.stream()
                .map(e -> new FieldError(e.path().getFirst(), e.invalidString()))
                .toList()
                : Collections.emptyList();
    }
}
