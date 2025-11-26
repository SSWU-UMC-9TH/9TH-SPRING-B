package spring.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getMyReviews(Long memberId, String storeName, Double star) {
        return reviewRepository.findMyReviews(memberId, storeName, star)
                .stream()
                .map(r -> new ReviewResponseDto(
                        r.getId(),
                        r.getMember().getNickname(),
                        r.getStore().getName(),
                        r.getContent(),
                        r.getStar(),
                        r.getCreatedAt()
                ))
                .toList();

    }
}
