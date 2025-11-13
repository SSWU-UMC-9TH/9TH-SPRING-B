package spring.umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.req.ReviewReqDto;
import spring.umc.domain.review.dto.res.ReviewResDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.domain.review.service.query.ReviewQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 목록
    @GetMapping("/my")
    public ApiResponse<List<ReviewResDto.MyReviewItem>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star //별점별 (5점, 4점대, 3점대 …)
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.findMyReviews(memberId, storeId, storeName, star)
        );
    }

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResDto.Created> createReview(
            @PathVariable Long memberId,
            @RequestBody @Valid ReviewReqDto.Create req
    ) {
        Long reviewId = reviewCommandService.writeReview(memberId, req.getStoreId(), req.getStar(), req.getContent());
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreated(reviewId)
        );
    }
}
