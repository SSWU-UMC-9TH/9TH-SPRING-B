package spring.umc.domain.member.service.query;

import spring.umc.domain.member.dto.res.MemberResDTO;

public interface AddressQueryService {
    MemberResDTO.LegalDong getLegalDongInfo(Long memberId);

}
