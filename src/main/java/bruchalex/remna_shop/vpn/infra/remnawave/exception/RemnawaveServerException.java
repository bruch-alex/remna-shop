package bruchalex.remna_shop.vpn.infra.remnawave.exception;

public class RemnawaveServerException extends RemnawaveApiException {
    public RemnawaveServerException(String status, RemnawaveErrorResponse errorBody) {
        super(errorBody);
    }
}
