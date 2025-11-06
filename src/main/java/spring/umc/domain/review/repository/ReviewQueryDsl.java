package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import spring.umc.domain.review.dto.ReviewDto;


import java.util.List;

public interface ReviewQueryDsl {

    // 내가 작성한 리뷰보기 API
    List<ReviewDto> findMyReviews(
            Predicate predicate
    );
}
