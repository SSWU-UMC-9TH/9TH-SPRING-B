package spring.umc.domain.review.converter;

import spring.umc.domain.review.dto.res.ReviewResDto;
import spring.umc.domain.review.entity.Review;

public class ReviewConverter {
    public static ReviewResDto.Created toCreated(Long reviewId) {
        return ReviewResDto.Created.builder()
                .reviewId(reviewId)
                .build();
    }
}
