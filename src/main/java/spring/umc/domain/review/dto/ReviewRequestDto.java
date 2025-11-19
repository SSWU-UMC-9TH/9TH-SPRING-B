package spring.umc.domain.review.dto;

import java.util.List;

public class ReviewRequestDto {

    public record CreateDto(
            Integer star,
            String content,
            List<String> images
    ) {}

}
