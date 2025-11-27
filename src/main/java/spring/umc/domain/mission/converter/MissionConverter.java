package spring.umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    // 진행중 페이지 변환 (커서 기반)
    public static MissionResDTO.CursorPage<MissionResDTO.OngoingItem> toOngoingPage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDTO.OngoingItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .storeName((String) r[3])
                        .createdAt((LocalDateTime) r[4])
                        .endedAt((LocalDate) r[5])
                        .complete((Boolean) r[6])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDTO.CursorPage.<MissionResDTO.OngoingItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 내가 진행 중인 미션 목록 (page 기반)
    public static MissionResDTO.OngoingListDTO toOngoingListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.OngoingListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toOngoingItem)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
    private static MissionResDTO.OngoingItem toOngoingItem(Mission mission) {
        return MissionResDTO.OngoingItem.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .storeName(mission.getStore().getName())
                .createdAt(mission.getCreatedAt())
                .endedAt(mission.getEndedAt())
                .complete(false) // 진행 중 목록이므로 false 고정
                .build();
    }

    // 미션 도전하기
    // 단일 MemberMission -> OngoingItem
    public static MissionResDTO.OngoingItem toOngoingItem(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MissionResDTO.OngoingItem.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .storeName(mission.getStore().getName())
                .complete(memberMission.isComplete())
                .build();
    }

    // 진행 완료 페이지
    public static MissionResDTO.CursorPage<MissionResDTO.CompletedItem> toCompletedPage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDTO.CompletedItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .storeName((String) r[3])
                        .complete((Boolean) r[4])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDTO.CursorPage.<MissionResDTO.CompletedItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 도전 가능 페이지
    public static MissionResDTO.CursorPage<MissionResDTO.ChallengableItem> toChallengablePage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDTO.ChallengableItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .endedAt((LocalDate) r[3])
                        .storeName((String) r[4])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDTO.CursorPage.<MissionResDTO.ChallengableItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 달성한 미션 개수
    public static MissionResDTO.CompletedCount toCompletedCount(Long memberId, Long count) {
        return MissionResDTO.CompletedCount.builder()
                .memberId(memberId)
                .count(count)
                .build();
    }

    // 특정 가게의 미션 목록
    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(MissionConverter::toStoreMissionDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(
            Mission mission
    ) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .condition(mission.getCondition())
                .point(mission.getPoint())
                .createdAt(mission.getCreatedAt())
                .endedAt(mission.getEndedAt())
                .storeName(mission.getStore().getName())
                .build();
    }
}
