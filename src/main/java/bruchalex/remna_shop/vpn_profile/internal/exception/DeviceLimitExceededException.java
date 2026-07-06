package bruchalex.remna_shop.vpn_profile.internal.exception;

public class DeviceLimitExceededException extends RuntimeException {
    public DeviceLimitExceededException(String message) {
        super(message);
    }
}
