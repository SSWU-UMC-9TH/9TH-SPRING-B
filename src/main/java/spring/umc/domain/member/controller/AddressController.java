package spring.umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.member.dto.res.MemberResDto;
import spring.umc.domain.member.service.query.AddressQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class AddressController {

    private final AddressQueryService addressQueryService;

    @GetMapping("/{memberId}/legal-dong")
    public ApiResponse<MemberResDto.LegalDong> getLegalDong(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                addressQueryService.getLegalDongInfo(memberId)
        );
    }
}
