package spring.umc.domain.review.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;

import java.time.LocalDate;

public class ReviewConverter {

    // Page<Review> → ReviewPreviewListDto
    public static ReviewResponseDto.ReviewPreviewListDto toPreviewListDto(Page<Review> reviewPage) {
        return ReviewResponseDto.ReviewPreviewListDto.builder()
                .reviewList(
                        reviewPage.getContent().stream()
                                .map(ReviewConverter::toPreviewDto)
                                .toList()
                )
                .listSize(reviewPage.getSize())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    // Review → ReviewPreviewDto
    public static ReviewResponseDto.ReviewPreviewDto toPreviewDto(Review review) {
        return ReviewResponseDto.ReviewPreviewDto.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getNickname())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
