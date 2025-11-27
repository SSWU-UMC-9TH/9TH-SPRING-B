package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.res.MissionResDTO;

public interface MemberMissionCommandService {

    // 미션 도전하기
    MissionResDTO.OngoingItem challengeMission(Long memberId, Long missionId);

    // 미션 완료로 변경하기
    MissionResDTO.CompletedItem completeMission(Long memberId, Long missionId);

}
