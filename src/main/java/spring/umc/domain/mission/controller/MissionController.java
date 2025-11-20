package spring.umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.mission.dto.MissionChallengeRequest;
import spring.umc.domain.mission.dto.MissionChallengeResponse;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.mission.service.MissionService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionRepository missionRepository;
    private final MissionService missionService;

    @PostMapping("/challenge")
    public ApiResponse<MissionChallengeResponse> challengeMission(
            @Valid @RequestBody MissionChallengeRequest request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionService.challengeMission(request)
        );
    }
}
