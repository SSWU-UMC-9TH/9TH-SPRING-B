package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.umc.domain.mission.entity.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        JOIN m.store s
        JOIN s.location l
        WHERE l.name = :locationName
        AND m.deadline > CURRENT_TIMESTAMP
        ORDER BY m.deadline ASC
    """)
    Page<Mission> findAvailableMissionsByLocationName(
            @Param("locationName") String locationName,
            Pageable pageable
    );

    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}
