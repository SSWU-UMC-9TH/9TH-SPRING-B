package spring.umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.exception.MissionException;
import spring.umc.domain.mission.exception.code.MissionErrorCode;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 미션 도전하기
    @Override
    @Transactional
    public MissionResDTO.OngoingItem challengeMission(Long memberId, Long missionId) {

        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 미션 존재 여부 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 3. 이미 도전한 미션인지 중복 체크
        if (memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }

        // 4. MemberMission 생성 (도전 상태: isComplete = false)
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        memberMissionRepository.save(memberMission);

        // 5. 방금 도전한 미션을 진행중 미션 DTO로 변환해서 반환
        return MissionConverter.toOngoingItemFromMemberMission(memberMission);
    }

    // 진행 중인 미션 완료로 바꾸기
    // 진행 중인 미션 → 완료로 변경 후 해당 미션 조회
    @Override
    @Transactional
    public MissionResDTO.CompletedItem completeMission(Long memberId, Long missionId) {

        // memberMission 조회
        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        // 이미 완료된 미션인지 체크
        if (memberMission.isComplete()) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        // 상태 변경
        memberMission.complete();

        // 저장 (JPA 변경감지)
        memberMissionRepository.save(memberMission);

        // 변경된 미션 엔티티 조회
        Mission mission = memberMission.getMission();

        // DTO 변환 후 반환
        return MissionConverter.toCompletedItem(mission);
    }

}
