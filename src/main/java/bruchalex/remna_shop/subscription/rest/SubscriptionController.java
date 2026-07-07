package bruchalex.remna_shop.subscription.rest;

import bruchalex.remna_shop.subscription.ActivateTrialPeriodUseCase;
import bruchalex.remna_shop.subscription.rest.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final ActivateTrialPeriodUseCase activateTestPeriodUseCase;

    @PostMapping("/trial")
    public ResponseEntity<SubscriptionResponse> trial(@AuthenticationPrincipal Authentication auth) {
        var response = activateTestPeriodUseCase.activateTrial(UUID.fromString(auth.getName()));
        return ResponseEntity.ok(response);
    }
    // get my subscriptions /me
    // getSubscriptionByUserEmail (admins) GET /{email}
    // getSubscriptionByUserUUID (admins) GET /{uuid}
    // addDaysToAllSubscriptions(Integer days) (admins) POST /add-days/7
}
