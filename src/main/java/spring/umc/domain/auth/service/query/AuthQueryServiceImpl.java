package spring.umc.domain.auth.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.umc.domain.auth.converter.AuthConverter;
import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;
import spring.umc.domain.auth.exception.AuthException;
import spring.umc.domain.auth.exception.code.AuthErrorCode;
import spring.umc.domain.user.CustomUserDetails;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.user.repository.UserRepository;
import spring.umc.global.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthQueryServiceImpl implements AuthQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public AuthResDto.LoginDTO login(
            AuthReqDto.@Valid LoginDTO dto
    ) {

        // User 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new AuthException(AuthErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), user.getPassword())){
            throw new AuthException(AuthErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return AuthConverter.toLoginDTO(user, accessToken);
    }

}
