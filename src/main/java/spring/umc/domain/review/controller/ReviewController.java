package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.review.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/my")
    public ResponseEntity<List<ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required=false) String storeName,
            @RequestParam(required=false) Double star
    ) {
        List<ReviewResponseDto> result = reviewService.getMyReviews(memberId, storeName, star);
        return ResponseEntity.ok(result);
    }
}
