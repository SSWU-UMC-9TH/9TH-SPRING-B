package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_FOUND(HttpStatus.OK,
            "MISSION200_1",
            "미션을 성공적으로 조회했습니다."),
    MISSION_UPDATED(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션 상태가 완료로 변경되었습니다."),
    MISSION_CHALLENGED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 도전 중인 미션에 추가되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
