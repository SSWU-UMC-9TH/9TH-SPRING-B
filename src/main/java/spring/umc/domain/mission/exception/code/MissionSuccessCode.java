package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_FOUND(HttpStatus.FOUND,
            "MISSION200_1",
            "성공적으로 미션을 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
