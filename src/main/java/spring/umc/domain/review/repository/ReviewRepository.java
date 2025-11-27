package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}

