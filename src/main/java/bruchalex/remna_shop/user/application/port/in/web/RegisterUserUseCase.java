package bruchalex.remna_shop.user.application.port.in.web;

import java.time.Instant;

public interface RegisterUserUseCase {
    Result execute(Command request);

    record Command(String email, String password) {}
    record Result(String email, Instant createdAt) {}
}
