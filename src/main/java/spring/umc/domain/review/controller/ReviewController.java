package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.review.dto.ReviewDto;
import spring.umc.domain.review.service.ReviewQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/my")
    public List<ReviewDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star //별점별 (5점, 4점대, 3점대 …)
    ) {
        return reviewQueryService.findMyReviews(memberId, storeId, storeName, star);
    }
}
