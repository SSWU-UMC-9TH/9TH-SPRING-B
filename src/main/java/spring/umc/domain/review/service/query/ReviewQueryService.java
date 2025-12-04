package spring.umc.domain.review.service.query;

import spring.umc.domain.review.dto.res.ReviewResDTO;
import spring.umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

    // 내가 작성한 리뷰 목록
    ReviewResDTO.MyReviewListDTO findMyReviews(
            Long memberId,
            Long storeId,
            String storeName,
            Integer star,
            Integer page

    );

    // 검색 API
    List<Review> searchReview(
            String filter,
            String type
    ) throws Exception;

    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );
}
