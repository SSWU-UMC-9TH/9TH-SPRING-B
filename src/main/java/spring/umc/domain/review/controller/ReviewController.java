package spring.umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.req.ReviewReqDTO;
import spring.umc.domain.review.dto.res.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.domain.review.service.query.ReviewQueryService;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 목록 API
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star, //별점별 (5점, 4점대, 3점대 …)
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReviews(memberId, storeId, storeName, star, page));
    }

    // 가게에 리뷰 추가하기
    @PostMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.Created> createReview(
            @PathVariable Long memberId,
            @RequestBody @Valid ReviewReqDTO.Create req
    ) {
        Long reviewId = reviewCommandService.writeReview(memberId, req.getStoreId(), req.getStar(), req.getContent());
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreated(reviewId)
        );
    }

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String filter,
            @RequestParam String type
    ) throws Exception {

        // 서비스에게 요청
        List<Review> result =  reviewQueryService.searchReview(filter, type);
        return result;
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}
