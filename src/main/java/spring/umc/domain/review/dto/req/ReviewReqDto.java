package spring.umc.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDto {
    @Getter
    public static class Create {
        @NotNull
        private Long storeId;

        @Min(0) @Max(5)
        private double star;

        @NotBlank
        private String content;
    }
}
