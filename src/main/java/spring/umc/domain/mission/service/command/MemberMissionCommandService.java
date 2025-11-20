package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.res.MissionResDto;

public interface MemberMissionCommandService {

    // 미션 도전하기
    MissionResDto.OngoingItem challenge(Long memberId, Long missionId);
}
