package spring.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
