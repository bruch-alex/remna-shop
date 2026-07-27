package bruchalex.remna_shop.vpn.infra.remnawave.exception;

public class RemnawaveResponseParseException extends RuntimeException {
    public RemnawaveResponseParseException(String status, String errorBody) {
        super("Status: " + status + ", error: " + errorBody);
    }
}