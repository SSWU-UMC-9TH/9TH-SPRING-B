package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.dto.res.ReviewResDTO;
import spring.umc.domain.review.entity.QReply;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResDTO.MyReviewDTO> findMyReviews (
        Predicate predicate,
        PageRequest pageRequest
    ) {
        
        // Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QReply reply = QReply.reply;

        return queryFactory
                .select(Projections.constructor(
                        ReviewResDTO.MyReviewDTO.class,
                        review.id,
                        review.content,
                        review.star,
                        reply.content // 없으면 null
                ))
                .from(review)
                .join(review.store, store)
                .leftJoin(review.reply, reply) // 답글은 선택적이므로 LEFT JOIN
                .where(predicate) // 조립된 조건 적용
                .offset(pageRequest.getOffset()) // 페이징 // 건너뛰는 행의 수
                .limit(pageRequest.getPageSize()) // 해당 페이지에서 가져올 개수
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public long countMyReviews(Predicate predicate) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        Long count = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(predicate)
                .fetchOne();

        // fetchOne()이 null을 반환할 수 있으므로, null이면 0으로 처리
        return count != null ? count : 0L;
    }
}
