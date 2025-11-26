package spring.umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import spring.umc.domain.member.entity.QMember;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;

    @Override
    public List<Review> findMyReviews(Long memberId, String storeName, Double star) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QStore store= QStore.store;
        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder()
                .and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(store.name.contains(storeName));
        }

        if (star > 0) {
            builder.and(review.star.eq(star));
        }

        return query.selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.member, member).fetchJoin()
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();

    }
}
