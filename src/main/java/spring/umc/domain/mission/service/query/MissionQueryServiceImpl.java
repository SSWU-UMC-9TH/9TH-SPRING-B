package spring.umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.res.MissionResDto;
import spring.umc.domain.mission.exception.MissionException;
import spring.umc.domain.mission.exception.code.MissionErrorCode;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 한 번에 조회할 개수 (LIMIT)
    // LIMIT 고정
    private static final int PAGE_SIZE_DEFAULT = 3;
    private static final int PAGE_SIZE_CHALLENGE = 4;

    /**
     * <진행 중> 미션 목록 조회 (커서 기반 페이징)
     *
     * - offset을 사용하지 않고, where 조건으로 커서를 처리
     * - pageable은 limit 역할만 함
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDto.CursorPage<MissionResDto.OngoingItem> getOngoingMissions(Long memberId, Long cursor){
        // Pageable을 고정 limit(3)으로 설정
        // 커서 기반 페이징이기 때문에, offset은 0으로 고정
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        List<Object[]> rows = missionRepository.findOngoingMissions(memberId, cursor, pageable);
        if (rows == null) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toOngoingPage(rows, PAGE_SIZE_DEFAULT);
    }

    /**
     * <진행 완료> 미션 목록 조회 (커서 기반 페이징)
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDto.CursorPage<MissionResDto.CompletedItem> getCompletedMissions(Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        List<Object[]> rows = missionRepository.findCompletedMissions(memberId, cursor, pageable);
        if (rows == null) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toCompletedPage(rows, PAGE_SIZE_DEFAULT);
    }

    /**
     * (홈화면)
     * <도전 가능> 미션 목록 조회 (커서 기반 페이징)
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDto.CursorPage<MissionResDto.ChallengableItem> getChallengableMissions(String legalDongCode, Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_CHALLENGE);

        List<Object[]> rows = missionRepository.findChallengableMissions(legalDongCode, memberId, cursor, pageable);
        if (rows == null) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toChallengablePage(rows, PAGE_SIZE_CHALLENGE);
    }

    /**
     * (홈화면)
     * 달성한 미션 개수 조회
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDto.CompletedCount getCompletedMissionCount(Long memberId) {
        Long count = memberMissionRepository.countByMemberIdAndIsCompleteTrue(memberId);

        if (count == null) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toCompletedCount(memberId, count);
    }
}

