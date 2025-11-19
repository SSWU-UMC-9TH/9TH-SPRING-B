package spring.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.review.dto.MyReviewResponseDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.user.entity.MissionStatus;
import spring.umc.domain.user.entity.UserMission;
import spring.umc.domain.user.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public Review createReview(Long userMissionId, String content, int star) {
        // 1. 쿼리로 리뷰 작성이 가능한 UserMission을 조회+검증
        UserMission userMission = userMissionRepository
                .findCompletedMissionWithoutReview(userMissionId, MissionStatus.COMPLETED)
                .orElseThrow(() -> new IllegalStateException("리뷰를 작성할 수 없는 미션입니다."));

        // 2. 조회에 성공했다면 해당 UserMission 정보를 바탕으로 리뷰 생성
        if (star < 1 || star > 5) {
            throw new IllegalArgumentException("1~5점 사이의 별점을 입력해주세요.");
        }

        Review newReview = Review.builder()
                .user(userMission.getUser())
                .store(userMission.getMission().getStore())
                .userMission(userMission) // 리뷰와 완료된 미션 연결
                .content(content)
                .star(star)
                .build();

        // 3. 생성된 리뷰 저장
        return reviewRepository.save(newReview);
    }

    @Transactional
    public Page<MyReviewResponseDto> getMyReviews(Long userId,
                                                  Long storeId,
                                                  String storeName,
                                                  Integer star,
                                                  int page,
                                                  int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewRepository.searchMyReviews(userId, storeId, storeName, star, pageable);
    }
}
