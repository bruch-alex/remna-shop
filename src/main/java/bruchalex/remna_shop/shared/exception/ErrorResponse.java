package bruchalex.remna_shop.shared.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String message, OffsetDateTime timestamp, List<ValidationError> fieldValidationErrors) {

    public ErrorResponse(String message) {
        this(message, OffsetDateTime.now(), null);
    }

    public ErrorResponse(String message, List<ValidationError> fieldValidationErrors) {
        this(message, OffsetDateTime.now(), fieldValidationErrors);
    }
}
