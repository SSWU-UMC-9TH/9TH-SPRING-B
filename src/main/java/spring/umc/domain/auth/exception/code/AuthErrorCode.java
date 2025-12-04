package spring.umc.domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    INVALID(HttpStatus.UNAUTHORIZED,
            "AUTH401_1",
            "제대로 된 비밀번호를 입력해주세요."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "AUTH404_1",
                    "해당 사용자를 찾지 못했습니다."),
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
