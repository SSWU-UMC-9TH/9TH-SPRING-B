package spring.umc.domain.review.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import spring.umc.domain.review.dto.res.ReviewResDTO;
import spring.umc.domain.review.entity.Review;

import java.time.LocalDate;
import java.util.List;

public class ReviewConverter {

    // 내가 작성한 리뷰 목록
    /*
        QueryDSL에서 이미 리뷰 엔티티가 아닌 DTO(MyReviewDTO)로 바로 조회되므로,
        엔티티 → DTO 변환 컨버터(toMyReviewDTO)는 필요하지 않음
        But, 조회된 DTO 목록 + 페이징 메타 정보를
        MyReviewListDTO로 조립하기 위한 컨버터(toMyReviewListDTO)는 필요함
     */
    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(
            List<ReviewResDTO.MyReviewDTO> contents,
            PageRequest pageRequest,
            long totalElements
    ) {
        /* ceil - 올림
            totalPage = 전체 데이터 개수 / 페이지 크기
            ex: (57 / 10 = 5.7)  → 올림 = 6 */
        int totalPage = (int) Math.ceil((double) totalElements / pageRequest.getPageSize());

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(contents.stream().toList())
                .listSize(contents.size())
                .totalPage(totalPage)
                .totalElements(totalElements)
                .isFirst(pageRequest.getPageNumber() == 0) // PageRequest는 0-based index
                .isLast(pageRequest.getPageNumber() + 1 >= totalPage) // totalPage는 1-based
                .build();
    }

    // 리뷰 추가
    public static ReviewResDTO.Created toCreated(Long reviewId) {
        return ReviewResDTO.Created.builder()
                .reviewId(reviewId)
                .build();
    }

    // 가게의 리뷰 목록
    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
