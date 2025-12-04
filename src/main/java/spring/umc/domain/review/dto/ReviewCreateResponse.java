package spring.umc.domain.review.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewCreateResponse {
    private Long reviewId;
    private Long memberId;
    private Long storeId;
    private Double star;
    private String content;
}
