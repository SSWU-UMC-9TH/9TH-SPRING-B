package spring.umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

public class ReviewResDto {

    @Getter
    @Builder
    public static class MyReviewItem {
        private final Long id;
        private final String content;
        private final Double star;
        private final String reply; // 없으면 null
    }

    @Getter
    @Builder
    public static class Created {
        private final Long reviewId;
    }

}
