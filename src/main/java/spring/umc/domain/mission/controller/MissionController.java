package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.common.dto.PageResponseDto;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.exception.code.MissionSuccessCode;
import spring.umc.domain.mission.service.command.MissionCommandService;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.domain.review.dto.ReviewRequestDto;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.global.annotation.paging.PageParam;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 나중에
    @PostMapping("/{userMissionId}/reviews")
    public ApiResponse<ReviewResponseDto.CreateDto> createReview(
            @PathVariable Long userMissionId,
            @RequestBody @Valid ReviewRequestDto.CreateDto dto,
            Long userId
            // 추후 로그인 구현 시 아래 사용
            // @AuthUser Long userId
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewCommandService.createReview(userMissionId, userId, dto)
        );
    }

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResponseDto.Challenge> challengeMission(
            @PathVariable Long missionId,
            Long userId
            // 추후 로그인 구현 시 아래 사용
            // @AuthUser Long userId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                missionCommandService.startMission(userId, missionId)
        );
    }

    @GetMapping("/me/{userId}")
    @Operation(
            summary = "내가 진행중인 미션 목록",
            description = "userId 에 해당하는 사용자의 진행중인 미션을 10개씩 페이징하여 조회합니다."
    )
    public ApiResponse<PageResponseDto<MissionResponseDto.MyMissionDto>> getMyInProgressMissions(
            @PathVariable Long userId,
            // 추후 로그인 구현 시 아래 사용
            // @AuthUser Long userId
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @PageParam Integer page
    ) {
        Page<MissionResponseDto.MyMissionDto> result = missionQueryService.getMyInProgressMissions(userId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.OK, PageResponseDto.from(result));
    }


}
