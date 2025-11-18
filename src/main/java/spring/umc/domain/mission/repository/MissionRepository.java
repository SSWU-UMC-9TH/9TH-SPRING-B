package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * <진행 중> 미션 목록 조회 (커서 기반 페이징)
     */
    @Query("""
                select m.id, m.point, m.condition, s.name, mm.isComplete
                from Mission m
                        join MemberMission mm on mm.mission = m
                        join m.store s
                where mm.member.id = :memberId
                        and mm.isComplete = false
                        and m.endedAt > CURRENT_DATE
                        and (:cursor is null or m.id < :cursor)
                order by m.id desc          
            """)
    List<Object[]> findOngoingMissions(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    /**
     * <진행 완료> 미션 목록 조회 (커서 기반 페이징)
     */
    @Query("""
                select m.id, m.point, m.condition, s.name, mm.isComplete
                from Mission m
                        join MemberMission mm on mm.mission = m
                        join m.store s
                where mm.member.id = :memberId
                        and mm.isComplete = true
                        and (:cursor is null or m.id < :cursor)
                order by m.id desc          
            """)
    List<Object[]> findCompletedMissions(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    /**
     *  (홈화면)
     *  <도전 가능> 미션 목록 조회 (커서 기반 페이징)
     */
    @Query("""
                select m.id, m.point, m.condition, m.endedAt, s.name
                from Mission m
                        join m.store s
                        join s.location l
                where l.legalDongCode = :legalDongCode
                        and m.endedAt > CURRENT_DATE
                        and not exists (
                                select 1
                                from MemberMission mm
                                where mm.member.id = :memberId
                                      and mm.mission = m
                        )
                        and (:cursor is null or m.id < :cursor)
                order by m.id desc          
            """)
    List<Object[]> findChallengableMissions(
            @Param("legalDongCode") String legalDongCode,
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
