package bruchalex.remna_shop.vpn.application;

import bruchalex.remna_shop.vpn.application.port.in.SetNewDeviceLabelUseCase;
import bruchalex.remna_shop.vpn.application.port.out.persistence.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SetNewDeviceLabelService implements SetNewDeviceLabelUseCase {

    private final ProfileRepository profileRepository;

    @Override
    public Result execute(Command command) {
        var profile = profileRepository.findById(command.userId()).orElseThrow();
        var renamed = profile.renameDevice(command.hwid(), command.newLabel());
        profileRepository.save(profile);
        return new Result(renamed.getDeviceId(), renamed.getLabel());
    }
}
