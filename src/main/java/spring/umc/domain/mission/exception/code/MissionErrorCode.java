package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "USM404_1",
            "사용자 미션을 찾지 못했습니다."),

    ALREADY_CHALLENGED(HttpStatus.CONFLICT,
            "USM409_1",
            "이 미션이 이미 존재합니다.")
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
