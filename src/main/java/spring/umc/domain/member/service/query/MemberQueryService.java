package spring.umc.domain.member.service.query;

import spring.umc.domain.member.dto.res.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.MyPage getMyPage(Long memberId);

}
