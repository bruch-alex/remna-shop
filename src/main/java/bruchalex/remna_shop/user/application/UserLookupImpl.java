package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.domain.UserId;
import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.user.in.api.UserLookup;
import bruchalex.remna_shop.user.in.api.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserLookupImpl implements UserLookup {
    private final UserRepository userRepo;

    @Override
    public Optional<UserSummary> findById(UUID id) {
        return userRepo.findById(UserId.of(id))
                .map(user -> new UserSummary(
                        user.getId().value(),
                        user.getEmail().value(),
                        user.getVpnProfileUuid(),
                        user.getRole().getValue(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                ));
    }

    @Override
    public boolean existsById(UUID id) {
        return userRepo.existsById(UserId.of(id));
    }
}
