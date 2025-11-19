package spring.umc.domain.review.dto;

import lombok.Builder;

public class ReviewResponseDto {

    @Builder
    public record CreateDto(
            Long reviewId
    ) {}
}
