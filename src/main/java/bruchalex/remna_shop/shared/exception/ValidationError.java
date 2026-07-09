package bruchalex.remna_shop.shared.exception;

public record ValidationError(
        String field,
        String message
) {
}
