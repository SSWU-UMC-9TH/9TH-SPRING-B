package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.PageRequest;
import spring.umc.domain.review.dto.res.ReviewResDTO;


import java.util.List;

public interface ReviewQueryDsl {

    // 내가 작성한 리뷰보기 API
    List<ReviewResDTO.MyReviewDTO> findMyReviews(
            Predicate predicate,
            PageRequest pageRequest
    );

    long countMyReviews(Predicate predicate);
}
