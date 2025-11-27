package spring.umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.mission.service.command.MemberMissionCommandService;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionQueryService missionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    // <진행 중> 미션 목록 조회 (커서 기반 페이징)
    @GetMapping(value = "/members/{memberId}/missions", params = "status=ongoing")
    public ApiResponse<MissionResDTO.CursorPage<MissionResDTO.OngoingItem>> ongoing(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getOngoingMissions(memberId, cursor)
        );
    }

    // <진행 완료> 미션 목록 조회 (커서 기반 페이징)
    @GetMapping(value = "/members/{memberId}/missions", params = "status=completed")
    public ApiResponse<MissionResDTO.CursorPage<MissionResDTO.CompletedItem>> completed(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getCompletedMissions(memberId, cursor)
        );
    }

    // (홈화면)
    // <도전 가능> 미션 목록 조회 (커서 기반 페이징)
    @GetMapping(value = "/members/{memberId}/missions/challengable")
    public ApiResponse<MissionResDTO.CursorPage<MissionResDTO.ChallengableItem>> challengable(
            @PathVariable Long memberId,
            @RequestParam String legalDongCode,
            @RequestParam(required = false) Long cursor
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getChallengableMissions(legalDongCode, memberId, cursor)
        );
    }

    // (홈화면)
    // 달성한 미션 개수 조회
    @GetMapping("/members/{memberId}/missions/completed/count")
    public ApiResponse<MissionResDTO.CompletedCount> completedCount(
            @PathVariable Long memberId
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getCompletedMissionCount(memberId)
        );
    }

    // 특정 가게의 미션 목록 API
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {

        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_FOUND,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }


    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @PostMapping("/members/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResDTO.OngoingItem> challenge(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ){
        MissionResDTO.OngoingItem result = memberMissionCommandService.challenge(memberId, missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
