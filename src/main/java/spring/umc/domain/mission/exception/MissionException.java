package spring.umc.domain.mission.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(BaseErrorCode code) {
            super(code);
    }
}