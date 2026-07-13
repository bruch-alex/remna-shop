package bruchalex.remna_shop.user.in.api;

import java.util.Optional;
import java.util.UUID;

public interface UserLookup {

    Optional<UserSummary> findById(UUID id);
    boolean existsById(UUID id);
}
