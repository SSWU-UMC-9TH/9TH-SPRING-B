package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.review.dto.MyReviewResponseDto;
import spring.umc.domain.review.entity.Review;

public interface ReviewQueryDsl {
    /**
     * @param userId   내가 작성한 리뷰만 (필수)
     * @param storeId  가게 ID 필터 (선택)
     * @param storeName 가게명 부분일치 필터 (선택)
     * @param star 정수 별점 필터(1~5점), null이면 전체
     */
    Page<MyReviewResponseDto> searchMyReviews(Long userId,
                                              Long storeId,
                                              String storeName,
                                              Integer star,
                                              Pageable pageable);
}
