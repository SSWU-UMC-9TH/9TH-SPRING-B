package spring.umc.domain.member.service.query;

import jakarta.validation.Valid;
import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
