package spring.umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.exception.code.StoreException;
import spring.umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

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
    public MissionResDTO.CursorPage<MissionResDTO.OngoingItem> getOngoingMissions(Long memberId, Long cursor){
        // Pageable을 고정 limit(3)으로 설정
        // 커서 기반 페이징이기 때문에, offset은 0으로 고정
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        List<Object[]> rows = missionRepository.findOngoingMissions(memberId, cursor, pageable);

        return MissionConverter.toOngoingCursorPage(rows, PAGE_SIZE_DEFAULT);
    }

    // 내가 진행 중인 미션 목록 (page 기반)
    @Override
    public MissionResDTO.OngoingListDTO getOngoingMissionsByPage(
            Long memberId,
            Integer page   // 1-based
    ) {
        // 1-based → 0-based 변환
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Mission> result = missionRepository.findOngoingMissionsByPage(memberId, pageRequest);

        return MissionConverter.toOngoingListDTO(result);
    }

    /**
     * <진행 완료> 미션 목록 조회 (커서 기반 페이징)
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.CursorPage<MissionResDTO.CompletedItem> getCompletedMissions(Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_DEFAULT);

        List<Object[]> rows = missionRepository.findCompletedMissions(memberId, cursor, pageable);

        return MissionConverter.toCompletedCursorPage(rows, PAGE_SIZE_DEFAULT);
    }

    /**
     * (홈화면)
     * <도전 가능> 미션 목록 조회 (커서 기반 페이징)
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.CursorPage<MissionResDTO.ChallengableItem> getChallengableMissions(String legalDongCode, Long memberId, Long cursor){
        var pageable  = PageRequest.of(0, PAGE_SIZE_CHALLENGE);

        List<Object[]> rows = missionRepository.findChallengableMissions(legalDongCode, memberId, cursor, pageable);

        return MissionConverter.toChallengableCursorPage(rows, PAGE_SIZE_CHALLENGE);
    }

    /**
     * (홈화면)
     * 달성한 미션 개수 조회
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.CompletedCount getCompletedMissionCount(Long memberId) {
        Long count = memberMissionRepository.countByMemberIdAndIsCompleteTrue(memberId);

        return MissionConverter.toCompletedCount(memberId, count);
    }

    // 특정 가게의 미션 목록 조회
    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.StoreMissionListDTO getStoreMissions(
            Long storeId,
            Integer page
    ){
        // 1. 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. PageRequest: 프론트 page(1-based) → 0-based 변환, size=10
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 특정 가게의 미션 목록 페이징 조회
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        // 4. Converter로 응답 DTO 변환
        return MissionConverter.toStoreMissionListDTO(result);

    }
}

