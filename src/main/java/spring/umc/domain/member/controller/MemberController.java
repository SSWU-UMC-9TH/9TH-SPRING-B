package spring.umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;
import spring.umc.domain.member.exception.code.MemberSuccessCode;
import spring.umc.domain.member.service.command.MemberCommandService;
import spring.umc.domain.member.service.query.MemberQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResDTO.MyPage> getMyPage(@PathVariable Long memberId){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberQueryService.getMyPage(memberId)
        );
    }
}
