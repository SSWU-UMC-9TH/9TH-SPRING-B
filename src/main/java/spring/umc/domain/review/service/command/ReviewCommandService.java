package spring.umc.domain.review.service.command;

public interface ReviewCommandService {
    Long writeReview(Long memberId, Long storeId, double star, String content);
}

