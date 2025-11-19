package spring.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "S200_1",
            "요청이 성공했습니다."),

    CREATED(HttpStatus.CREATED,
            "S201_1",
            "데이터가 성공적으로 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "S202_1",
            "요청이 성공적으로 받아들여졌습니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
               "S204_1",
            "응답 본문에 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
