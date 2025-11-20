package spring.umc.domain.review.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // 미션 사진에 나와있는 가게별 작성된 리뷰 보기 구현 (최신순으로 구현)
    @EntityGraph(attributePaths = {"member"})
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);

    // 특정 회원이 작성한 리뷰 리스트 (마이페이지부분에서 연결됨)
    List<Review> findByMemberId(Long memberId);
}
