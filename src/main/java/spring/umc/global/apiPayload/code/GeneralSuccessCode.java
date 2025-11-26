package spring.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "COMMON201",
            "성공적으로 생성되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204",
            "처리 성공했지만 반환할 데이터가 없습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
