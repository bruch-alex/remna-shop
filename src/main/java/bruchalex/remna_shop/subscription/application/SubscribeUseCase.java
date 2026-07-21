package bruchalex.remna_shop.subscription.application;

import bruchalex.remna_shop.subscription.domain.SubscriptionRepositoryPort;
import bruchalex.remna_shop.tariff.in.api.TariffLookup;
import bruchalex.remna_shop.tariff.in.api.TariffSummary;
import bruchalex.remna_shop.user.in.api.UserLookup;
import bruchalex.remna_shop.user.in.api.UserSummary;
import bruchalex.remna_shop.user.in.api.UserUpdater;
import bruchalex.remna_shop.vpn.in.api.VpnService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscribeUseCase {

    private final SubscriptionRepositoryPort subscriptionRepo;
    private final UserLookup userLookup;
    private final UserUpdater userUpdater;
    private final TariffLookup tariffLookup;
    private final VpnService vpnService;

    @Transactional
    public SubscribeResult execute(SubscribeCommand command) {

        // 1. Load user
        UserSummary user = userLookup.findById(command.userId()).orElseThrow();
        // 2. Load tariff
        TariffSummary tariff = tariffLookup.findTariff(command.tariffId()).orElseThrow();
        // 3. if vpn profile is missing create disabled profile
//        VpnProfileSummary = vpnService.
        // 4. Create subscription
        // 5. Save pending subscription
        // 6. Update vpn profile
        // vpnProvider.setNewVpnProfileTerms();
        // 7. Change subscription status to active
        return null;
    }
}
