package spring.umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "USM401_1",
            "완료되지 않은 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
