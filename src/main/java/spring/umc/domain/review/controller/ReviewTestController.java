package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.common.dto.PageResponseDto;
import spring.umc.domain.review.dto.MyReviewResponseDto;
import spring.umc.domain.review.service.ReviewService;
import org.springframework.data.domain.Page;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class ReviewTestController {

    private final ReviewService reviewService;

    /**
     *  [GET] /api/test/reviews?userId=1&storeName=반이학생마라탕마라반&star=5
     *  [GET] /api/test/reviews?userId=1&storeId=10
     */
    @GetMapping("/reviews")
    public ApiResponse<PageResponseDto<MyReviewResponseDto>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MyReviewResponseDto> dto = reviewService.getMyReviews(userId, storeId, storeName, star, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, PageResponseDto.from(dto));
    }
}
