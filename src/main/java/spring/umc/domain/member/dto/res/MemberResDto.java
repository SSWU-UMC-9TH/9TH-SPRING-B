package spring.umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDto {

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
