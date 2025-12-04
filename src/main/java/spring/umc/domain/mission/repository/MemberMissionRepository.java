package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.umc.domain.mission.entity.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행 중 미션
    @Query("SELECT um FROM MemberMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.member.id = :memberId " +
            "AND um.isComplete = false")
    Page<MemberMission> findByOngoingMissionByMemberId(Long memberId, Pageable pageable);

    // 진행 완료 미션 (isComplete = true)
    @Query("SELECT um FROM MemberMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.member.id = :memberId " +
            "AND um.isComplete = true")
    Page<MemberMission> findCompletedMissionsByMemberId(Long memberId, Pageable pageable);

    // 미션 매칭시 사용 (지금 이미 매칭되어서 진행 중인 미션인지 확인)
    boolean existsByMemberIdAndMissionIdAndIsCompleteFalse(Long memberId, Long missionId);
}
