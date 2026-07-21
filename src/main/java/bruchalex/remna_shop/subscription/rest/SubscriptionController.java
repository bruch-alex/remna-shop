package bruchalex.remna_shop.subscription.rest;

import bruchalex.remna_shop.shared.auth.AuthUser;
import bruchalex.remna_shop.subscription.application.SubscribeCommand;
import bruchalex.remna_shop.subscription.application.SubscribeUseCase;
import bruchalex.remna_shop.subscription.domain.Subscription;
import bruchalex.remna_shop.subscription.rest.dto.SubscribeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscribeUseCase  subscribeUseCase;

    @PostMapping
    public Subscription subscribe(@AuthenticationPrincipal AuthUser authUser, @RequestBody SubscribeRequest request) {
        var command = new SubscribeCommand(UUID.fromString(authUser.userUuid()), request.tariffId());
        var result = subscribeUseCase.execute(command);
        return null;
    }
}
