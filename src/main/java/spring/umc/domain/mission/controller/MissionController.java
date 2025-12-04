package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MemberMissionResponseDto;
import spring.umc.domain.mission.dto.MissionChallengeRequest;
import spring.umc.domain.mission.dto.MissionChallengeResponse;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.mission.service.MissionService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;
import spring.umc.global.validator.ValidPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

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

    @GetMapping("/store")
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "storeId에 해당하는 가게의 미션 목록을 페이징해 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "목록 반환 성공")
    })
    public ApiResponse<MissionResponseDto.MissionListDto> getMissionsByStore(
            @RequestParam Long storeId,
            @ValidPage Pageable pageable
    ) {
        MissionResponseDto.MissionListDto result =
                missionService.getMissionsByStore(storeId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/ongoing")
    @Operation(
            summary = "진행중인 미션 목록 조회",
            description = "내가 진행 중인 미션 목록을 페이징해 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "목록 반환 성공")
    })
    public ApiResponse<MemberMissionResponseDto.MemberMissionListDto> getOngoingMissions(
            @RequestParam Long memberId,
            @ValidPage Pageable pageable
    ) {
        var result = missionService.getOngoingMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
