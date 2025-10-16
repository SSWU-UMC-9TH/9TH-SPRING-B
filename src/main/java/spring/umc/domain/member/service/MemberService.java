package spring.umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.dto.MyPageResponse;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 마이페이지 조회
     */
    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Long memberId) {

        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return new MyPageResponse(
                m.getName(),
                m.getEmail(),
                m.getPhone(),
                m.getPhoneVerified(),
                m.getPoint()
        );
    }
}
