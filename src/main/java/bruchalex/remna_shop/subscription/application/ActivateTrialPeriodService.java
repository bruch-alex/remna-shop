package bruchalex.remna_shop.subscription.application;

import bruchalex.remna_shop.subscription.ActivateTrialPeriodUseCase;
import bruchalex.remna_shop.subscription.domain.Subscription;
import bruchalex.remna_shop.subscription.domain.SubscriptionRepositoryPort;
import bruchalex.remna_shop.subscription.domain.SubscriptionTerms;
import bruchalex.remna_shop.subscription.domain.SubscriptionType;
import bruchalex.remna_shop.subscription.domain.exception.TrialAlreadyUsedException;
import bruchalex.remna_shop.subscription.rest.dto.SubscriptionResponse;
import bruchalex.remna_shop.tariff.GetTestTariffDetailsUseCase;
import bruchalex.remna_shop.user.UserOperations;
import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileCommand;
import bruchalex.remna_shop.vpn_profile.CreateNewVpnProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivateTrialPeriodService implements ActivateTrialPeriodUseCase {
    private final UserOperations userOperations;
    private final CreateNewVpnProfileUseCase createProfileUseCase;
    private final SubscriptionRepositoryPort subscriptionRepo;
    private final GetTestTariffDetailsUseCase getTestTariffDetailsUseCase;


    @Override
    public SubscriptionResponse activateTrial(UUID userId) {
        // 1. Check if main subscription already used trial
        if (subscriptionRepo.existsSubscriptionByUserUuidAndType(userId, SubscriptionType.TRIAL)) {
            throw new TrialAlreadyUsedException();
        }

        // 2. Get current trial tariff
        var trialTariff = getTestTariffDetailsUseCase.getTrialTariffDetails();

        // 3. Pause other user subscriptions if it has one
        subscriptionRepo.pauseSubscriptionByUserUuid(userId);

        // 4. Create new VpnProfile
        var vpnProfile = createProfileUseCase.createNewVpnProfile(new CreateNewVpnProfileCommand(
                Instant.now().plus(trialTariff.durationDays()),
                trialTariff.devicesAllowed(),
                trialTariff.trafficLimitGb()));

        // 4. Create new active trial subscription
        var sub = Subscription.builder()
                .type(SubscriptionType.TRIAL)
                .mainVpnProfileUuid(vpnProfile.profileUuid())
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plus(trialTariff.durationDays()))
                .subscriptionTerms(new SubscriptionTerms(
                        trialTariff.id(),
                        trialTariff.price(),
                        trialTariff.durationDays(),
                        trialTariff.trafficLimitGb(),
                        trialTariff.devicesAllowed()
                ))
                .userUuid(userId)
                .build();
        var saved = subscriptionRepo.save(sub);

        return new SubscriptionResponse(
                vpnProfile.subscriptionLink(),
                sub.getSubscriptionTerms().traffic(),
                sub.getSubscriptionTerms().devicesLimit()
        );
    }
}
