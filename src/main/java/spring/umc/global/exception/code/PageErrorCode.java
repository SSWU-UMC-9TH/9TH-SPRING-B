package spring.umc.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    INVALID_PAGE(HttpStatus.BAD_REQUEST,
            "PAGE400_1",
            "page 값은 1 이상의 정수여야 합니다."),

;
    private final HttpStatus status;
    private final String code;
    private final String message;
}



