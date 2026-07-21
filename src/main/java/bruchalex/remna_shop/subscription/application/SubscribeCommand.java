package bruchalex.remna_shop.subscription.application;

import java.util.UUID;

public record SubscribeCommand(
        UUID userId,
        UUID tariffId
) {
}
