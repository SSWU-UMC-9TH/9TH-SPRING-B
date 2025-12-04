package spring.umc.domain.member.converter;

import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.ProviderType;
import spring.umc.domain.member.enums.Status;
import spring.umc.global.auth.enums.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO, Salted Password, Role -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .gender(dto.gender())
                .status(Status.ACTIVE)
                .point(0)
                .phoneVerified(false)
                .providerType(ProviderType.LOCAL)
                .providerUid(dto.email())
                .build();
    }

    // 로그인 DTO 변환
    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

    // 객체 -> DTO
    public static MemberResDTO.MyPage toMyPage(Member m) {
        return MemberResDTO.MyPage.builder()
                .name(m.getName())
                .email(m.getEmail())
                .phone(m.getPhone())
                .phoneVerified(m.getPhoneVerified())
                .point(m.getPoint())
                .build();
    }

    public static MemberResDTO.LegalDong toLegalDong(Address a) {
        return MemberResDTO.LegalDong.builder()
                .legalDong(a.getLegalDong())
                .legalDongCode(a.getLegalDongCode())
                .build();
    }

}
