package spring.umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.exception.code.MissionSuccessCode;
import spring.umc.domain.mission.service.command.MissionCommandService;
import spring.umc.domain.review.dto.ReviewRequestDto;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

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

}
