package bruchalex.remna_shop.vpn.infra.remnawave.exception;

public class RemnawaveClientException extends RemnawaveApiException {
    public RemnawaveClientException(String status, RemnawaveErrorResponse errorBody) {
        super(errorBody);
    }
}
