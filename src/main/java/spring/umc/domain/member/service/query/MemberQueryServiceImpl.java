package spring.umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.res.MemberResDto;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements  MemberQueryService {

    private final MemberRepository memberRepository;

    /**
     * 마이페이지 조회
     */
    @Override
    @Transactional(readOnly = true)
    public MemberResDto.MyPage getMyPage(Long memberId) {

        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER404_1));

        return MemberConverter.toMyPage(m);
    }
}
