package bruchalex.remna_shop.user.application;

import bruchalex.remna_shop.user.domain.UserId;
import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.user.in.api.UserUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdaterImpl implements UserUpdater {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void assignVpnProfile(UUID userUuid, UUID vpnProfileUuid) {
        var user = userRepository.findById(UserId.of(userUuid)).orElseThrow();
        user.assignVpnProfile(vpnProfileUuid);
        userRepository.save(user);
    }
}
