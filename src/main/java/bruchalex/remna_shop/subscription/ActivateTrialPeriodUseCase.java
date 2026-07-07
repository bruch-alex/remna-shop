package bruchalex.remna_shop.subscription;

import bruchalex.remna_shop.subscription.rest.dto.SubscriptionResponse;

import java.util.UUID;

public interface ActivateTrialPeriodUseCase {
    SubscriptionResponse activateTrial(UUID userId);
}
