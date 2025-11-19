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
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthCommandService authCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<AuthResDto.JoinDto> signUp(
            @RequestBody @Valid AuthReqDto.JoinDto dto) {
        return ApiResponse.onSuccess(AuthSuccessCode.FOUND, authCommandService.signUp(dto));
    }
}
