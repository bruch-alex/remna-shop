package bruchalex.remna_shop.vpn.adapter.in.web;

import bruchalex.remna_shop.shared.auth.AuthUser;
import bruchalex.remna_shop.vpn.adapter.in.web.dto.*;
import bruchalex.remna_shop.vpn.application.port.in.DeviceManagementUseCase;
import bruchalex.remna_shop.vpn.application.port.in.ProfileManagementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vpn-profile")
@RequiredArgsConstructor
public class VpnController {

    private final ProfileManagementUseCase profileManagementUseCase;
    private final DeviceManagementUseCase deviceManagementUseCase;

    private final RestMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getProfiles(@AuthenticationPrincipal AuthUser authUser) {
        var result = profileManagementUseCase.getProfiles(new ProfileManagementUseCase.GetProfilesCommand(UUID.fromString(authUser.userUuid())));
        var response = result.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody CreateProfileRequest createProfileRequest
    ){
        var command =
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable("profileId") UUID profileId,
            @AuthenticationPrincipal AuthUser authUser) {
        var command = new ProfileManagementUseCase.GetProfileSummaryCommand(
                UUID.fromString(authUser.userUuid()),
                profileId
        );
        var result = profileManagementUseCase.getProfileSummary(command);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{profileId}/rename")
    public ResponseEntity<ProfileResponse> renameProfile(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("profileId") UUID profileId,
            @RequestBody RenameProfileRequest request
    ) {
        var command = new ProfileManagementUseCase.RenameProfileCommand(
                UUID.fromString(authUser.userUuid()),
                profileId,
                request.name()
        );

        var result = profileManagementUseCase.renameProfile(command);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sync")
    public ResponseEntity<List<ProfileResponse>> syncProfile(@AuthenticationPrincipal AuthUser authUser) {
        var command = new ProfileManagementUseCase.SyncProfileCommand(
                UUID.fromString(authUser.userUuid()),
                authUser.userEmail()
        );
        var result = profileManagementUseCase.syncRemoteProfiles(command);
        var response = result.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{profileId}/device/{hwid}/remove")
    public ResponseEntity<String> removeDevice(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("hwid") String hwid,
            @PathVariable("profileId") UUID profileId) {
        var command = new DeviceManagementUseCase.RemoveDeviceCommand(
                UUID.fromString(authUser.userUuid()),
                profileId,
                hwid
        );
        deviceManagementUseCase.removeDevice(command);
        return ResponseEntity.ok("Device removed");
    }

    @PostMapping("/{profileId}/device/{hwid}/rename")
    public ResponseEntity<DeviceResponse> renameDevice(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("profileId") UUID profileId,
            @PathVariable("hwid") String hwid,
            @RequestBody RenameDeviceRequest request) {
        var command = new DeviceManagementUseCase.RenameDeviceCommand(
                UUID.fromString(authUser.userUuid()),
                profileId,
                hwid,
                request.newName()
        );
        var result = deviceManagementUseCase.renameDevice(command);
        return ResponseEntity.ok(mapper.toResponse(result));
    }
}
