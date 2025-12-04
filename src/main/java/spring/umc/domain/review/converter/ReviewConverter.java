package spring.umc.domain.review.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;

import java.time.LocalDate;

public class ReviewConverter {

    // result -> DTO
    public static ReviewResponseDto.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResponseDto.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getNickname())
                .score(Float.valueOf(review.getStar()))
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
