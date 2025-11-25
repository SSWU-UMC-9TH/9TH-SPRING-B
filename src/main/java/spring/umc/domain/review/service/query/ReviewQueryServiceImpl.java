package spring.umc.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.review.dto.res.ReviewResDto;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResDto.MyReviewItem> findMyReviews(Long memberId, Long storeId, String storeName, Integer star) {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용 (동적 쿼리)

        builder.and(review.member.id.eq(memberId)) // 기본 조건 (필수), 로그인한 사용자가 작성한 리뷰만 조회
                .and(eqStoreId(storeId)) // 가게 필터
                .and(containsStoreName(storeName)) //가게 이름 필터
                .and(filterStarRange(star)); // 별점 필터


        // BooleanBuilder로 조건을 누적한 후,
        // Predicate로 조립하여 Repository로 전달
        Predicate predicate = builder;

        // 조립된 조건(Predicate)을 기반으로 QueryDSL 쿼리 실행
        return reviewRepository.findMyReviews(predicate);

    }

    // 가게 필터 (선택)
    private BooleanExpression eqStoreId(Long storeId) {
        QStore store = QStore.store;
        if (storeId == null) return null;   // null이면 조건 안 붙이게 함
        return store.id.eq(storeId);
    }

    // 가게 이름 필터 (선택)
    private BooleanExpression containsStoreName(String storeName) {
        QStore store = QStore.store;
        if (storeName == null || storeName.isBlank()) return null;
        return store.name.containsIgnoreCase(storeName.trim());
    }

    // 별점대 필터 (선택)
    private BooleanExpression filterStarRange(Integer star) {
        QReview review = QReview.review;
        if (star == null) return null;

        if (star == 5) {
            return review.star.eq(5.0);
        } else if (star >= 0 && star <= 4) {
            return review.star.goe(star.doubleValue())
                    .and(review.star.lt(star.doubleValue() + 1.0));
        }
        return null; // 범위 밖이면 조건 안 붙임
    }

}
