package bruchalex.remna_shop.user.application.usecase;

import bruchalex.remna_shop.user.web.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.web.dto.RegisterUserResponse;
import bruchalex.remna_shop.user.domain.*;
import bruchalex.remna_shop.user.domain.exception.UserAlreadyExistsException;
import bruchalex.remna_shop.vpn_profile.ProvisionVpnProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;
    private final ProvisionVpnProfileUseCase provisionVpnProfileUseCase;


    public RegisterUserResponse execute(RegisterUserRequest request) {
        var email = new Email(request.email());
        var hashedPassword = new HashedPassword(passwordEncoder.encode(request.password()));

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }

        var newUser = MyUser.create(
                email,
                hashedPassword,
                UserRole.USER
        );

        var saved = userRepository.save(newUser);
        provisionVpnProfileUseCase.createVpnProfile(null);
        return RegisterUserResponse.from(saved);
    }
}
