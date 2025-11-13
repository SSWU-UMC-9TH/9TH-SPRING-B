package spring.umc.domain.mission.converter;

import spring.umc.domain.mission.dto.res.MissionResDto;

import java.time.LocalDate;
import java.util.List;

public class MissionConverter {

    // 진행중
    public static MissionResDto.CursorPage<MissionResDto.OngoingItem> toOngoingPage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDto.OngoingItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .storeName((String) r[3])
                        .complete((Boolean) r[4])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDto.CursorPage.<MissionResDto.OngoingItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 잔행 완료
    public static MissionResDto.CursorPage<MissionResDto.CompletedItem> toCompletedPage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDto.CompletedItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .storeName((String) r[3])
                        .complete((Boolean) r[4])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDto.CursorPage.<MissionResDto.CompletedItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 도전 가능
    public static MissionResDto.CursorPage<MissionResDto.ChallengableItem> toChallengablePage(List<Object[]> rows, int limit) {
        var items = rows.stream().map(r ->
                MissionResDto.ChallengableItem.builder()
                        .missionId((Long) r[0])
                        .point((Integer) r[1])
                        .condition((String) r[2])
                        .endedAt((LocalDate) r[3])
                        .storeName((String) r[4])
                        .build()
        ).toList();

        Long next = (rows.size() == limit) ? (Long) rows.get(rows.size() - 1)[0] : null;

        return MissionResDto.CursorPage.<MissionResDto.ChallengableItem>builder()
                .items(items)
                .nextCursor(next)
                .size(items.size())
                .build();
    }

    // 달성한 미션 개수
    public static MissionResDto.CompletedCount toCompletedCount(Long memberId, Long count) {
        return MissionResDto.CompletedCount.builder()
                .memberId(memberId)
                .count(count)
                .build();
    }
}
