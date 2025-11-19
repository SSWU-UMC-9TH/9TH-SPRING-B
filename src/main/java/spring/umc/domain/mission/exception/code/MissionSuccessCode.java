package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CHALLENGED(HttpStatus.OK,
            "MS200_1",
                    "성공적으로 미션을 요청했습니다."),
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
