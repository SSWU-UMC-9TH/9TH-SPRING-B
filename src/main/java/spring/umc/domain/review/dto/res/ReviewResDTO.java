package spring.umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    // 내가 작성한 리뷰 목록
    @Builder
    public record MyReviewListDTO(
            List<MyReviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Getter
    @Builder
    public static class MyReviewDTO {
        private final Long id;
        private final String name;
        private final String content;
        private final Double star;
        private final String reply; // 없으면 null
        private final LocalDate createdAt;
    }

    // 가게에 리뷰 추가
    @Getter
    @Builder
    public static class Created {
        private final Long reviewId;
    }

    // 가게의 리뷰 목록
    @Builder
    public record ReviewPreViewListDTO (
        List<ReviewPreViewDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}

}
