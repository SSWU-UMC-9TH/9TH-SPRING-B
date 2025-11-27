package spring.umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.user.entity.MissionStatus;
import spring.umc.domain.user.entity.UserMission;
import spring.umc.domain.user.repository.UserMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private static final int PAGE_SIZE = 10;

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    // 특정 가게의 미션 목록
    public Page<MissionResponseDto.StoreMissionDto> getStoreMissions(Long storeId, int pageZeroBased) {

        PageRequest pageRequest = PageRequest.of(pageZeroBased, PAGE_SIZE);
        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, pageRequest);

        List<MissionResponseDto.StoreMissionDto> content =
                MissionResponseDto.toStoreMissionDtoList(missionPage);

        return new PageImpl<>(content, pageRequest, missionPage.getTotalElements());
    }

    // 내가 진행중인 미션 목록
    public Page<MissionResponseDto.MyMissionDto> getMyInProgressMissions(Long userId, int pageZeroBased) {

        PageRequest pageRequest = PageRequest.of(pageZeroBased, PAGE_SIZE);

        return userMissionRepository.findMyMissionsPage(
                userId,
                MissionStatus.IN_PROGRESS,
                pageRequest
        );
    }
}
