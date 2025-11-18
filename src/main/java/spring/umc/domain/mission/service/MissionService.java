package spring.umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    // 한 번에 조회할 개수 (LIMIT)
    private static final int PAGE_SIZE_DEFAULT = 3;
    private static final int PAGE_SIZE_CHALLENGE = 4;

    /**
     * <진행 중> 미션 목록 조회 (커서 기반 페이징)
     *
     * - offset을 사용하지 않고, where 조건으로 커서를 처리
     * - pageable은 limit 역할만 함
     */
    @Transactional(readOnly = true)
    public List<Object[]> getOngoingMissions(Long memberId, Long cursor){
        // Pageable을 고정 limit(3)으로 설정
        // 커서 기반 페이징이기 때문에, offset은 0으로 고정
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        return missionRepository.findOngoingMissions(memberId, cursor, pageable);
    }

    /**
     * <진행 완료> 미션 목록 조회 (커서 기반 페이징)
     */
    @Transactional(readOnly = true)
    public List<Object[]> getCompletedMissions(Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        return missionRepository.findCompletedMissions(memberId, cursor, pageable);
    }

    /**
     * (홈화면)
     * <도전 가능> 미션 목록 조회 (커서 기반 페이징)
     */
    @Transactional(readOnly = true)
    public List<Object[]> getChallengableMissions(String legalDongCode, Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_CHALLENGE);

        return missionRepository.findChallengableMissions(legalDongCode, memberId, cursor, pageable);
    }
}
