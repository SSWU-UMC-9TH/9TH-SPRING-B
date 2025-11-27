package spring.umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.common.dto.PageResponseDto;
import spring.umc.domain.review.dto.MyReviewResponseDto;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.domain.review.service.ReviewService;
import spring.umc.domain.review.service.query.ReviewQueryService;
import spring.umc.global.annotation.paging.PageParam;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;

    @GetMapping("search")
    public List<Review> searchReview(
            @RequestParam String filter,
            @RequestParam String type
    ) throws Exception {

        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(filter, type);
        return result;
    }

    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, null);
    }

    @GetMapping("/me")
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "로그인한 사용자의 리뷰 목록을 페이징으로 조회합니다. " +
                    "프론트는 page=1 이상의 값을 전달해야 합니다."
    )
    public ApiResponse<PageResponseDto<MyReviewResponseDto>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @Parameter(description = "1 이상의 페이지 번호")
            @PageParam Integer page           // 1-based → Resolver가 0-based로 변환함
    ) {
        Page<MyReviewResponseDto> dto = reviewService.getMyReviews(userId, storeId, storeName, star, page);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, PageResponseDto.from(dto));
    }
}
