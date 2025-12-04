package spring.umc.domain.member.service.query;

import jakarta.validation.Valid;
import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.MyPage getMyPage(Long memberId);

    MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
