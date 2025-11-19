package spring.umc.domain.review.repository;

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
    public Page<MyReviewResponseDto> searchMyReviews(Long userId,
                                                     Long storeId,
                                                     String storeName,
                                                     Integer star,
                                                     Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        var query = queryFactory
                .select(Projections.constructor(
                        MyReviewResponseDto.class,
                        review.id,
                        store.name,
                        review.star,
                        review.content
                ))
                .from(review)
                .join(review.store, store)
                .where(
                        review.user.id.eq(userId),
                        storeId != null ? store.id.eq(storeId) : null,
                        storeName != null ? store.name.contains(storeName) : null,
                        star != null ? review.star.eq(star) : null
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<MyReviewResponseDto> content = query.fetch();

        Long totalCount = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        review.user.id.eq(userId),
                        storeId != null ? store.id.eq(storeId) : null,
                        storeName != null ? store.name.contains(storeName) : null,
                        star != null ? review.star.eq(star) : null
                )
                .fetchOne();

        long total = (totalCount != null) ? totalCount : 0L;

        return new PageImpl<>(content, pageable, total);

    }
}
