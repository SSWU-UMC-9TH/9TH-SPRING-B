package spring.umc.domain.mission.service.query;

import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;

public interface MissionQueryService {
    // <진행 중> 미션 목록 조회 (커서 기반 페이징)
    MissionResDTO.CursorPage<MissionResDTO.OngoingItem> getOngoingMissions(Long memberId, Long cursor);

    // 내가 진행 중인 미션 목록 (page 기반 페이징)
    MissionResDTO.OngoingListDTO getOngoingMissionsByPage(
            Long missionId,
            Integer page // 1-based
    );

    // <진행 완료> 미션 목록 조회 (커서 기반 페이징)
    MissionResDTO.CursorPage<MissionResDTO.CompletedItem> getCompletedMissions(Long memberId, Long cursor);

    // (홈화면)
    // <도전 가능> 미션 목록 조회 (커서 기반 페이징)
    MissionResDTO.CursorPage<MissionResDTO.ChallengableItem> getChallengableMissions(String legalDongCode, Long memberId, Long cursor);
    
    // (홈화면)
    // 달성한 미션 개수 조회
    MissionResDTO.CompletedCount getCompletedMissionCount(Long memberId);

    // 특정 가게의 미션 목록
    MissionResDTO.StoreMissionListDTO getStoreMissions(
            Long storeId,
            Integer page
    );
}
