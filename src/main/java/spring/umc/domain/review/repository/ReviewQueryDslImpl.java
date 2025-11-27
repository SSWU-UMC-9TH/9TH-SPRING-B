package spring.umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.dto.MyReviewResponseDto;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    // where절 빼먹는 거 없게 리팩토링 진행
    public Page<MyReviewResponseDto> searchMyReviews(
            Long userId,
            Long storeId,
            String storeName,
            Integer star,
            Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        // 1. 공통 where 조건 만들기
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.user.id.eq(userId));
        if (storeId != null) builder.and(store.id.eq(storeId));
        if (storeName != null) builder.and(store.name.contains(storeName));
        if (star != null) builder.and(review.star.eq(star));

        // 2. content 조회 쿼리
        List<MyReviewResponseDto> content = queryFactory
                .select(Projections.constructor(
                        MyReviewResponseDto.class,
                        review.id,
                        store.name,
                        review.star,
                        review.content
                ))
                .from(review)
                .join(review.store, store)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 3. count 쿼리는 select만 다르게
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
    
}
