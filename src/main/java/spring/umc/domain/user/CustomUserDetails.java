package spring.umc.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.umc.domain.user.entity.User;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    // 권한을 List 형태로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().toString());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 아이디를 반환 (여기선 이메일을 아이디로 사용!)
    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
