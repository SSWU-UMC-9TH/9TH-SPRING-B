package spring.umc.domain.member.service.command;

import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {

    // 회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
