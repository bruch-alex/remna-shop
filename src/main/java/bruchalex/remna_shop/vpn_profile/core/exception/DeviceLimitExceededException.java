package bruchalex.remna_shop.vpn_profile.core.exception;

public class DeviceLimitExceededException extends RuntimeException {
    public DeviceLimitExceededException(String message) {
        super(message);
    }
}
