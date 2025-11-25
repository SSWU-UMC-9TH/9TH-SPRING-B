package spring.umc.domain.member.converter;

import spring.umc.domain.member.dto.res.MemberResDto;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.entity.Member;

public class MemberConverter {

    // 객체 -> DTO
    public static MemberResDto.MyPage toMyPage(Member m) {
        return MemberResDto.MyPage.builder()
                .name(m.getName())
                .email(m.getEmail())
                .phone(m.getPhone())
                .phoneVerified(m.getPhoneVerified())
                .point(m.getPoint())
                .build();
    }

    public static MemberResDto.LegalDong toLegalDong(Address a) {
        return MemberResDto.LegalDong.builder()
                .legalDong(a.getLegalDong())
                .legalDongCode(a.getLegalDongCode())
                .build();
    }

}
