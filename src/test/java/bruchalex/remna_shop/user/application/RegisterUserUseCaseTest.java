package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.user.domain.exception.UserAlreadyExistsException;
import bruchalex.remna_shop.user.in.rest.dto.RegisterUserRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;


public class RegisterUserUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private final RegisterUserUseCase useCase =
            new RegisterUserUseCase(userRepository, passwordEncoder);

    @Test
    void cannotRegister_whenEmailExists() {
        var command = new RegisterUserRequest("existing@example.com", "password123", "password123");

        // given: a user with this email already exists
        when(userRepository.existsByEmail(new Email("existing@example.com")))
                .thenReturn(true);

        // when / then: registration is rejected
        Assertions.assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(UserAlreadyExistsException.class);

        // and: no new user is persisted, no password is hashed
        verify(userRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }
}
