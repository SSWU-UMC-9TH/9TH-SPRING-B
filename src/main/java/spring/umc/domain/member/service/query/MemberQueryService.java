package spring.umc.domain.member.service.query;

import spring.umc.domain.member.dto.res.MemberResDto;

public interface MemberQueryService {
    MemberResDto.MyPage getMyPage(Long memberId);

}
