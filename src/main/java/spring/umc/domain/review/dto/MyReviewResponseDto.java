package spring.umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class MyReviewResponseDto {
    private Long reviewId;
    private String storeName;
    private Integer star;
    private String content;

    public MyReviewResponseDto(Long reviewId, String storeName, Integer star, String content) {
        this.reviewId = reviewId;
        this.storeName = storeName;
        this.star = star;
        this.content = content;
    }
}
