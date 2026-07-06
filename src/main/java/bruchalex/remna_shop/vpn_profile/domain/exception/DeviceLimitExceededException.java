package bruchalex.remna_shop.vpn_profile.domain.exception;

public class DeviceLimitExceededException extends RuntimeException {
    public DeviceLimitExceededException(String message) {
        super(message);
    }
}
