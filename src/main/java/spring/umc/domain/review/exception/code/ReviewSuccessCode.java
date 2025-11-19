package spring.umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "201_1",
            "성공적으로 리뷰를 작성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
