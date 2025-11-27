package spring.umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.exception.MissionException;
import spring.umc.domain.mission.exception.code.MissionErrorCode;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.user.entity.MissionStatus;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.user.entity.UserMission;
import spring.umc.domain.user.exception.UserException;
import spring.umc.domain.user.exception.code.UserErrorCode;
import spring.umc.domain.user.repository.UserMissionRepository;
import spring.umc.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public MissionResponseDto.Challenge startMission(Long userId, Long missionId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 이미 도전한 미션인지 체크
        if (userMissionRepository.existsByUserIdAndMissionId(userId, missionId)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        userMissionRepository.save(userMission);

        return new MissionResponseDto.Challenge(userMission.getId());
    }
}
