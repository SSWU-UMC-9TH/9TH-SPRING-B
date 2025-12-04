package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션을 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "해당 회원의 도전 중인 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 도전한 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST,
            "MISSION400_2",
            "이미 완료된 미션입니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}