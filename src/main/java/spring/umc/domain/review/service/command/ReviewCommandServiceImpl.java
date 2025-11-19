package spring.umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.exception.MissionException;
import spring.umc.domain.mission.exception.code.MissionErrorCode;
import spring.umc.domain.review.dto.ReviewRequestDto;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.entity.ReviewImg;
import spring.umc.domain.review.exception.ReviewException;
import spring.umc.domain.review.exception.code.ReviewErrorCode;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.user.entity.MissionStatus;
import spring.umc.domain.user.entity.UserMission;
import spring.umc.domain.user.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final UserMissionRepository userMissionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResponseDto.CreateDto createReview(Long userMissionId, Long userId,
                                                    ReviewRequestDto.CreateDto dto) {
        
        // 나중에 사용자 인증 필요
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (!userMission.getStatus().equals(MissionStatus.COMPLETED)) {
            throw new ReviewException(ReviewErrorCode.UNAUTHORIZED);
        }

        Review review = Review.builder()
                .user(userMission.getUser())
                .store(userMission.getMission().getStore())
                .userMission(userMission)
                .star(dto.star())
                .content(dto.content())
                .build();

        reviewRepository.save(review);

        // 이미지 URL 리스트 처리 (테스트용)
        if (dto.images() != null) {
            int order = 0;
            for (String url : dto.images()) {
                ReviewImg img = new ReviewImg(url, order++);
                review.addImage(img); // 연관관계 편의 메서드 사용
            }
        }

        return new ReviewResponseDto.CreateDto(review.getId());
    }
}
