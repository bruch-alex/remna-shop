package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.shared.exception.ErrorResponse;
import bruchalex.remna_shop.vpn.domain.VpnProviderException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("bruchalex.remna_shop.vpn")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VpnExceptionHandler {

    @ExceptionHandler(VpnProviderException.class)
    public ResponseEntity<ErrorResponse> handleVpnException(
        VpnProviderException ex
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse(ex.getMessage())
        );
    }
}
