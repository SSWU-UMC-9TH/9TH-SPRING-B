package spring.umc.domain.review.repository;

import spring.umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {

    List<Review> findMyReviews(Long memberId, String storeName, Double star);
}
