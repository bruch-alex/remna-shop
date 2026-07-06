package bruchalex.remna_shop.user;

import java.util.UUID;

public interface UserOperations {
    UserInfo getUserInfo(UUID userUuid);
}
