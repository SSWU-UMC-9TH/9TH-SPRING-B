package spring.umc.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewCreateRequest {

    @NotNull
    private Long memberId;

    @NotBlank
    @Size(min = 5, max = 300)
    private String content;

    @NotNull
    private Long storeId;

    @NotNull
    @DecimalMax("5.0")
    @DecimalMin("0.0")
    private Double star;


}
