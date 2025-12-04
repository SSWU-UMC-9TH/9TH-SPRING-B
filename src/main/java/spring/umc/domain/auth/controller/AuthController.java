package spring.umc.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;
import spring.umc.domain.auth.exception.code.AuthSuccessCode;
import spring.umc.domain.auth.service.command.AuthCommandService;
import spring.umc.domain.auth.service.query.AuthQueryService;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthCommandService authCommandService;
    private final AuthQueryService authQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<AuthResDto.JoinDto> signUp(
            @RequestBody @Valid AuthReqDto.JoinDto dto) {
        return ApiResponse.onSuccess(AuthSuccessCode.CREATED, authCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<AuthResDto.LoginDTO> login(
            @RequestBody AuthReqDto.@Valid LoginDTO dto
    ){
        return ApiResponse.onSuccess(AuthSuccessCode.FOUND, authQueryService.login(dto));
    }
}
