package bruchalex.remna_shop.shared.auth;

public interface TokenGenerator {
    String generate(String userId, String role);
}
