package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.MissionResponseDto;

public interface MissionCommandService {
    MissionResponseDto.Challenge startMission(Long userId, Long missionId);
}
