package spring.umc.domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "USER401_1",
            "본인이 소유한 미션이 아닙니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "사용자를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
