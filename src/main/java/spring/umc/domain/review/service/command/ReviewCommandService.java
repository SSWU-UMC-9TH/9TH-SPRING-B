package spring.umc.domain.review.service.command;

import spring.umc.domain.review.dto.ReviewRequestDto;
import spring.umc.domain.review.dto.ReviewResponseDto;

public interface ReviewCommandService {
    ReviewResponseDto.CreateDto createReview(
            Long userMissionId, Long userId, ReviewRequestDto.CreateDto dto);
}

