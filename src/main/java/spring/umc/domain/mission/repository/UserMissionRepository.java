package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.umc.domain.mission.entity.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 진행 중 미션
    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.member.id = :memberId " +
            "AND um.isComplete = false")
    Page<UserMission> findByOngoingMissionByMemberId(Long memberId, Pageable pageable);

    // 진행 완료 미션 (isComplete = true)
    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.member.id = :memberId " +
            "AND um.isComplete = true")
    Page<UserMission> findCompletedMissionsByMemberId(Long memberId, Pageable pageable);
}
