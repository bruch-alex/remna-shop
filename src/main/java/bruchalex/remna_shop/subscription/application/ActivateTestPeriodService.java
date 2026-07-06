package bruchalex.remna_shop.subscription.application;

import bruchalex.remna_shop.subscription.ActivateTestPeriodUseCase;
import bruchalex.remna_shop.user.UserOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivateTestPeriodService implements ActivateTestPeriodUseCase {
    private final UserOperations userOperations;

    @Override
    public void activateTestPeriod(UUID userId) {
        var userInfo = userOperations.getUserInfo(userId).uuid();

        // guard: no double activation — a domain rule
        if (subs.existsForUser(uid))
            throw new TestPeriodAlreadyActivatedException(uid);

        // 1. resolve email (cross-module read)
        var contact = users.findContact(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 2. create the subscription aggregate (domain)
        var subscription = Subscription.startTestPeriod(uid, Clock.systemUTC());
        subs.save(subscription);

        // 3. create VPN profile via third-party (cross-module command → adapter)
        var profile = vpn.createProfile(
                new CreateVpnProfileRequest(userId, contact.email()));

        subscription.attachVpnProfile(profile.profileId());   // domain
        subs.save(subscription);

        // 4. build the subscription link
        return links.linkFor(subscription);
    }
}
