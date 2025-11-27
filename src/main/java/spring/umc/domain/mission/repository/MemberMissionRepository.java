package spring.umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.mission.entity.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    /**
     * (홈화면) 달성한 미션 개수 조회
     */
    Long countByMemberIdAndIsCompleteTrue(Long memberId);

    // 중복 도전 방지
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    // memberMission 조회
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

}
