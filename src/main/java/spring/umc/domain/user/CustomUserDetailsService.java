package spring.umc.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.user.exception.UserException;
import spring.umc.domain.user.exception.code.UserErrorCode;
import spring.umc.domain.user.repository.UserRepository;

// 🔐 보안 강화 팁 🔐
// 실제 애플리케이션에서는 로그인 시도 횟수 제한,
// 2단계 인증 등의 추가적인 보안 기능을 구현하는 것이 좋습니다.
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 User 조회
        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다: " + username));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}
