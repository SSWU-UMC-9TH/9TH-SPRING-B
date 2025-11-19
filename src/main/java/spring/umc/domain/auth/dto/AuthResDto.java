package spring.umc.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class AuthResDto {

    @Builder
    public record JoinDto(
            Long userId,
            LocalDateTime createdAt
    ){}
}
