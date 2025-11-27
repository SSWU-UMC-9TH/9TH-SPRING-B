package spring.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String storeName;
    private String nickname;
    private String content;
    private Double star;
    private LocalDateTime createdAt;

    @Builder
    public record ReviewPreviewListDto(
            List<ReviewPreviewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreviewDto(
            Long reviewId,
            String nickname,
            String storeName,
            Double star,
            String content,
            LocalDate createdAt
    ) {}

}
