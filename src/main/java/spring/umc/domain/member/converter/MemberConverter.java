package spring.umc.domain.member.converter;


import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;
import spring.umc.domain.member.entity.Member;
import spring.umc.global.auth.enums.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto, String password, Role role) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .nickname(dto.nickname())
                .password(password)
                .email(dto.email())
                .role(role)
                .addressDetail(dto.addressDetail())
                .sex(dto.sex())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member, String accessToken
    ) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
