package spring.umc.domain.mission.service.query;

import spring.umc.domain.mission.dto.res.MissionResDto;

public interface MissionQueryService {
    // <진행 중> 미션 목록 조회 (커서 기반 페이징)
    MissionResDto.CursorPage<MissionResDto.OngoingItem> getOngoingMissions(Long memberId, Long cursor);

    // <진행 완료> 미션 목록 조회 (커서 기반 페이징)
    MissionResDto.CursorPage<MissionResDto.CompletedItem> getCompletedMissions(Long memberId, Long cursor);

    // (홈화면)
    // <도전 가능> 미션 목록 조회 (커서 기반 페이징)
    MissionResDto.CursorPage<MissionResDto.ChallengableItem> getChallengableMissions(String legalDongCode, Long memberId, Long cursor);
    
    // (홈화면)
    // 달성한 미션 개수 조회
    MissionResDto.CompletedCount getCompletedMissionCount(Long memberId);
}
