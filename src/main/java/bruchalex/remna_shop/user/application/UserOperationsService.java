package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.UserInfo;
import bruchalex.remna_shop.user.UserOperations;
import bruchalex.remna_shop.user.domain.UserId;
import bruchalex.remna_shop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserOperationsService implements UserOperations {
    private final UserRepository userRepository;

    @Override
    public Optional<UserInfo> getUserInfo(UUID userUuid) {
        return userRepository.findById(UserId.of(userUuid))
                .map(user -> new UserInfo(user.getUuid().value(), user.getEmail().value()));
    }
}
