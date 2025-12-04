package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),


    INVALID(HttpStatus.BAD_REQUEST,
            "MEMBER404_2",
            "유효하지 않은 값의 입력입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

