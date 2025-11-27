package spring.umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.mission.converter.MemberMissionConverter;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.MemberMissionResponseDto;
import spring.umc.domain.mission.dto.MissionChallengeRequest;
import spring.umc.domain.mission.dto.MissionChallengeResponse;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.entity.MemberMission;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionChallengeResponse challengeMission(MissionChallengeRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        // 이미 진행 중인 미션인지 확인 (한 번 한 미션도 끝나면 다시 할 수 있다고 가정)

        if (memberMissionRepository.existsByMemberIdAndMissionIdAndIsCompleteFalse(request.getMemberId(), request.getMissionId())) {
            throw new IllegalArgumentException("이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);

        return MissionChallengeResponse.builder()
                .challengeId(saved.getId())
                .memberId(member.getId())
                .missionId(mission.getId())
                .isComplete(saved.getIsComplete())
                .build();
    }

    public MissionResponseDto.MissionListDto getMissionsByStore (Long storeId, Pageable pageable) {
        Page<Mission> missionPage = missionRepository.findByStoreId(storeId, pageable);

        return MissionConverter.toMissionListDto(missionPage);
    }

    public MemberMissionResponseDto.MemberMissionListDto getOngoingMissions (
            Long memberId,
            Pageable pageable) {
        Page<MemberMission> missionPage =
                memberMissionRepository.findByOngoingMissionByMemberId(memberId, pageable);

        return MemberMissionConverter.toMemberMissionListDto(missionPage);
    }
}
