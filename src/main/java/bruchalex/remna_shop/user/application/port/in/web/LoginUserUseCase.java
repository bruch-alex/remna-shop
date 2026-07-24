package bruchalex.remna_shop.user.application.port.in.web;

public interface LoginUserUseCase {
    Result execute(Command request);

    record Command(String email, String password){};
    record Result(String accessToken){};
}
