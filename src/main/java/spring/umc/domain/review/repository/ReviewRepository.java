package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.user.entity.User;

public interface ReviewRepository
        extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // 1. 내가 작성한 리뷰
    Page<Review> findAllByUser(User user, Pageable pageable);

    Page<Review> findAllByStore(Store store, Pageable pageable);
}
