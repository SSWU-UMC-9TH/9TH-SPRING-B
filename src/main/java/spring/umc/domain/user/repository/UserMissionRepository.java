package spring.umc.domain.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.user.dto.AvailableMissionDto;
import spring.umc.domain.user.dto.UserMissionDto;
import spring.umc.domain.user.entity.MissionStatus;
import spring.umc.domain.user.entity.UserMission;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {

    // 리뷰 작성하는 쿼리
    // 사용자 미션 상태가 'COMPLETED'이고, 리뷰가 아직 작성되지 않은 상태인 것을 조회
    @Query("SELECT um FROM UserMission um " +
            "LEFT JOIN Review r ON r.userMission = um " + // UserMission에 연결된 Review를 외부 조인
            "WHERE um.id = :userMissionId " +
            "  AND um.status = :status " +
            "  AND r.id IS NULL") // JOIN된 Review가 없을 경우 (=리뷰가 아직 작성되지 않음)
    Optional<UserMission> findCompletedMissionWithoutReview(@Param("userMissionId") Long userMissionId,
                                                            @Param("status") MissionStatus status);

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
    @Query("SELECT new spring.umc.domain.user.dto.UserMissionDto(um.status, m.name, s.name) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.id = :userId " +
            "  AND um.status = :status " +
            "  AND um.id > :cursorId " +
            "ORDER BY um.id ASC")
    // 데이터 목록 + 다음 페이지 존재 여부를 반환하기 위해서는 Slice 타입을 사용하는 것이 좋다고 함
    // 즉, 더보기 또는 무한 스크롤 같이 만들 때 사용하면 좋다...!?
    Slice<UserMissionDto> findMyMissions(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함
    @Query("SELECT new spring.umc.domain.user.dto.AvailableMissionDto(m.id, m.name, s.name, um.status) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE s.region.id = :regionId " +
            "  AND um.status = :status " +
            "  AND um.id > :cursorId " +
            "ORDER BY um.id ASC")
    Slice<AvailableMissionDto> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("status") MissionStatus status, // "NOT_STARTED"에 해당하는 Enum 값
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
    
    // 사용자가 해당 미션에 이미 도전했는지 확인
    boolean existsByUserIdAndMissionId(Long userId, Long missionId);
}