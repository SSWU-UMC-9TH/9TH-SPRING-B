package spring.umc.domain.member.converter;

import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Status;

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

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .gender(dto.gender())
                .status(Status.ACTIVE)
                .point(0)
                .phoneVerified(false)
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
