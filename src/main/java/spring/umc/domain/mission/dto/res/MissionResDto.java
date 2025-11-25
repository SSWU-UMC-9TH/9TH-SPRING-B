package spring.umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDto {

    @Getter
    @Builder
    public static class OngoingItem {
        private final Long missionId;
        private final Integer point;
        private final String condition;
        private final String storeName;
        private final boolean complete; // 항상 false지만 쿼리 결과 반영
    }

    @Getter
    @Builder
    public static class CompletedItem {
        private final Long missionId;
        private final Integer point;
        private final String condition;
        private final String storeName;
        private final boolean complete; // 항상 true지만 쿼리 결과 반영
    }

    @Getter
    @Builder
    public static class ChallengableItem {
        private final Long missionId;
        private final Integer point;
        private final String condition;
        private final LocalDate endedAt;
        private final String storeName;
    }

    // 커서 기반 공통 페이지 래퍼
    @Getter
    @Builder
    public static class CursorPage<T> {
        private final List<T> items; // 현재 페이지 아이템
        private final Long nextCursor; // 다음 커서(없으면 null)
        private final int size; // 반환된 개수
    }

    // (홈화면) 달성한 미션 개수 응답
    @Getter
    @Builder
    public static class CompletedCount {
        private final Long memberId;
        private final Long count;
    }

}
