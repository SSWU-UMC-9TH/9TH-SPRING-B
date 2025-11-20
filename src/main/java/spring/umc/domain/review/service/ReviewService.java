package spring.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.dto.ReviewCreateRequest;
import spring.umc.domain.review.dto.ReviewCreateResponse;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

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

    public ReviewCreateResponse createReview(ReviewCreateRequest request) {

        Member member = memberRepository.findByIdAndIsActiveTrue(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(request.getStar())
                .content(request.getContent())
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewCreateResponse.builder()
                .reviewId(saved.getId())
                .memberId(saved.getId())
                .storeId(store.getId())
                .star(saved.getStar())
                .content(saved.getContent())
                .build();

    }
}
