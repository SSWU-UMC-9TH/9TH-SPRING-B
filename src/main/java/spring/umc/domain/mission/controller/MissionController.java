package spring.umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.res.MissionResDto;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    // <진행 중> 미션 목록 조회 (커서 기반 페이징)
    @GetMapping("/ongoing")
    public ApiResponse<MissionResDto.CursorPage<MissionResDto.OngoingItem>> ongoing(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getOngoingMissions(memberId, cursor)
        );
    }

    // <진행 완료> 미션 목록 조회 (커서 기반 페이징)
    @GetMapping("/completed")
    public ApiResponse<MissionResDto.CursorPage<MissionResDto.CompletedItem>> completed(
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
    @GetMapping("/challengable")
    public ApiResponse<MissionResDto.CursorPage<MissionResDto.ChallengableItem>> challengable(
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
    @GetMapping("/completed/count")
    public ApiResponse<MissionResDto.CompletedCount> completedCount(
            @PathVariable Long memberId
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getCompletedMissionCount(memberId)
        );
    }
}
