package spring.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepsitory;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepsitory storeRepository;

    /**
     * 리뷰 작성 (사진 제외)
     */
    @Transactional
    public Review writeReview(Long memberId, Long storeId, int star, String content) {

        // 중복 작성 여부 확인
        boolean alreadyExists = !reviewRepository.existsByMemberIdAndStoreId(memberId, storeId);
        if (alreadyExists) {
            throw new IllegalStateException("이미 해당 가게에 리뷰를 작성한 적이 있습니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        if (star < 1 || star > 5) {
            throw new IllegalArgumentException("별점은 1~5 사이의 값이어야 합니다.");
        }

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(star)
                .content(content)
                .build();

        return reviewRepository.save(review); // INSERT 자동 실행

    }
}
