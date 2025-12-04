package spring.umc.domain.mission.service.query;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.MissionResponseDto;

public interface MissionQueryService {

    Page<MissionResponseDto.StoreMissionDto> getStoreMissions(Long storeId, int pageZeroBased);

    Page<MissionResponseDto.MyMissionDto> getMyInProgressMissions(Long userId, int pageZeroBased);
}
