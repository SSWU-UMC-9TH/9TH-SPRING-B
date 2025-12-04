package spring.umc.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class AuthResDto {

    @Builder
    public record JoinDto(
            Long userId,
            LocalDateTime createdAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Long userId,
            String accessToken
    ){}
}
