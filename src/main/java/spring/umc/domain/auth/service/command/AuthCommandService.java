package spring.umc.domain.auth.service.command;

import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;

public interface AuthCommandService {
    
    // 회원가입
    AuthResDto.JoinDto signUp(AuthReqDto.JoinDto dto);
}
