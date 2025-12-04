package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_SIGNUP_SUCCESS(
            HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입에 성공했습니다."
    ),
    MEMBER_LOGIN_SUCCESS(
            HttpStatus.OK,
            "MEMBER200_1",
            "로그인에 성공했습니다."
    ),
    MEMBER_FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
