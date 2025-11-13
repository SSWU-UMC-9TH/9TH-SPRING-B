package spring.umc.domain.review.service.query;

import spring.umc.domain.review.dto.res.ReviewResDto;

import java.util.List;

public interface ReviewQueryService {

    List<ReviewResDto.MyReviewItem> findMyReviews(Long memberId, Long storeId, String storeName, Integer star);
}
