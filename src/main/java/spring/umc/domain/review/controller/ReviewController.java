package spring.umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewCreateRequest;
import spring.umc.domain.review.dto.ReviewCreateResponse;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.review.service.ReviewService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/my")
    public ApiResponse<List<ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required=false) String storeName,
            @RequestParam(required=false) Double star
    ) {
        List<ReviewResponseDto> result = reviewService.getMyReviews(memberId, storeName, star);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping("/")
    public ApiResponse<ReviewCreateResponse> createReview(
            @Valid @RequestBody ReviewCreateRequest request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewService.createReview(request)
        );
    }

}
