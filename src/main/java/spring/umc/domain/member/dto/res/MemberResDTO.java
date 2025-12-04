package spring.umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    // 회원가입
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}

    @Getter
    @Builder
    public static class MyPage {
        private final String name;
        private final String email;
        private final String phone;
        private final boolean phoneVerified;
        private final int point;
    }

    @Getter
    @Builder
    public static class LegalDong {
        private final String legalDong;
        private final String legalDongCode;
    }
}
