package spring.umc.domain.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.test.converter.TestConverter;
import spring.umc.domain.test.dto.res.TestResDto;
import spring.umc.domain.test.exception.TestException;
import spring.umc.domain.test.service.query.TestQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDto.Testing> test() {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDto("This is Test!")
        );
    }

    // 예외 상황
    @GetMapping("/exception")
    public ApiResponse<TestResDto.Exception> exception(
            @RequestParam Long flag
    ) {
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                TestConverter.toExceptionDto("This is Test!")
        );
    }
}
