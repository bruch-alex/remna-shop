package bruchalex.remna_shop.user;

import java.util.Optional;
import java.util.UUID;

public interface UserOperations {
    Optional<UserInfo> getUserInfo(UUID userUuid);
}
