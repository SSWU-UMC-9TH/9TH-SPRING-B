package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.dto.ReviewDto;
import spring.umc.domain.review.entity.QReply;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewDto> findMyReviews (
        Predicate predicate
    ) {
        
        // Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QReply reply = QReply.reply;

        return queryFactory
                .select(Projections.constructor(
                        ReviewDto.class,
                        review.id,
                        review.content,
                        review.star,
                        reply.content // 없으면 null
                ))
                .from(review)
                .join(review.store, store)
                .leftJoin(review.reply, reply) // 답글은 선택적이므로 LEFT JOIN
                .where(predicate) // 조립된 조건 적용
                .fetch();

    }
}
