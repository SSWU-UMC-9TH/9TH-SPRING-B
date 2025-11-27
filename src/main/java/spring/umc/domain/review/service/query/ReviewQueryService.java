package spring.umc.domain.review.service.query;

import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

    // 검색 API
    List<Review> searchReview(
            String filter,
            String type
    ) throws Exception;

    ReviewResponseDto.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );
}
