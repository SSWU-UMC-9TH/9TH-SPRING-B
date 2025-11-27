package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.res.MissionResDTO;

public interface MemberMissionCommandService {

    // 미션 도전하기
    MissionResDTO.OngoingItem challenge(Long memberId, Long missionId);
}
