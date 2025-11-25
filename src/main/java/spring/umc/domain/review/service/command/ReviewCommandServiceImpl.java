package spring.umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exception.ReviewException;
import spring.umc.domain.review.exception.code.ReviewErrorCode;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepsitory;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepsitory storeRepository;

    /**
     * 리뷰 작성 (사진 제외)
     */
    @Override
    @Transactional
    public Long writeReview(Long memberId, Long storeId, double star, String content) {

        // 중복 작성 여부 확인
        boolean alreadyExists = reviewRepository.existsByMemberIdAndStoreId(memberId, storeId);
        if (alreadyExists) {
            throw new ReviewException(ReviewErrorCode.REVIEW409_1);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER404_1));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() ->
                        // 스토어 전용 코드가 있으면 사용, 없으면 리뷰 도메인 404 사용
                        new ReviewException(ReviewErrorCode.REVIEW404_1)
                );

        if (star < 0.0 || star > 5.0) {
            throw new ReviewException(ReviewErrorCode.REVIEW400_2);
        }

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(star)
                .content(content)
                .build();

        return reviewRepository.save(review).getId(); // INSERT 자동 실행

    }
}
