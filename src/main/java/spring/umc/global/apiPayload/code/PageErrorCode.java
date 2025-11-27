package spring.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    PAGE_LESS_THAN_ONE(
            HttpStatus.BAD_REQUEST,
            "PAGE400_1",
            "page는 1 이상이어야 합니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
