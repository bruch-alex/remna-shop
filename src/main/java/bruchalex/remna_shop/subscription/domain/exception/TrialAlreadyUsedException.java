package bruchalex.remna_shop.subscription.domain.exception;

public class TrialAlreadyUsedException extends RuntimeException {
    public TrialAlreadyUsedException() {
        super("Trial already used");
    }
}
