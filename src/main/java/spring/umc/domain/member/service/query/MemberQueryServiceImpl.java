package spring.umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.global.auth.CustomUserDetails;
import spring.umc.global.config.JwtUtil;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {

        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // CustomUserDetails로 감싸기
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 토큰 발급
        String token = jwtUtil.createAccessToken(userDetails);

        // 응답 DTO로 변환
        return MemberConverter.toLoginDTO(member, token);
    }
}
