package spring.umc.domain.review.dto;

public record ReviewDto (
    Long id,
    String content,
    Double star,
    String reply
){}
