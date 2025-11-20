package spring.umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.entity.ReviewReply;

import java.util.List;

@Repository
public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {
    // 미션 사진에 나와있는 답글 내역을 위한 구현
    List<ReviewReply> findByReviewId(long reviewId);
}
