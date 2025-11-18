package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.review.dto.ReviewDto;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> findMyReviews(Long memberId, Long storeId, String storeName, Integer star){
        
        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용 (동적 쿼리)

        // 기본 조건 (필수)
        // 로그인한 사용자가 작성한 리뷰만 조회
        builder.and(review.member.id.eq(memberId));

        // 가게 필터 (선택)
        if(storeId != null){
            builder.and(store.id.eq(storeId));
        } else if (storeName != null && !storeName.isBlank()){
            builder.and(store.name.containsIgnoreCase(storeName.trim()));
        }

        // 별점대 필터 (선택)
        if(star != null){
            if (star == 5) {
                builder.and(review.star.eq(5.0)); // 5점은 정확히 5.0만
            } else if (star >= 0 && star <= 4) { // 4점대, 3점대, 2점대...
                builder.and(review.star.goe(star.doubleValue())
                        .and(review.star.lt(star.doubleValue() + 1.0)));
            }
        }

        // BooleanBuilder로 조건을 누적한 후,
        // Predicate로 조립하여 Repository로 전달
        Predicate predicate = builder;

        // 조립된 조건(Predicate)을 기반으로 QueryDSL 쿼리 실행
        return reviewRepository.findMyReviews(predicate);

    }

}
