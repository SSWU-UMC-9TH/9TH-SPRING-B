package spring.umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewCreateRequest;
import spring.umc.domain.review.dto.ReviewCreateResponse;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.review.service.ReviewService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;
import spring.umc.global.validator.ValidPage;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/my")
    @Operation(
            summary = "내가 쓴 리뷰 조회",
            description = "페이징된 내 리뷰 목록을 가져와 보여줍니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    public ApiResponse<ReviewResponseDto.ReviewPreviewListDto> getMyReviews(
            @RequestParam Long memberId,
            @ValidPage Pageable pageable
    ) {
        ReviewResponseDto.ReviewPreviewListDto result =
                reviewService.getMyReviewList(memberId, pageable);

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
