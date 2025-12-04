package spring.umc.global.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class InvalidPageException extends GeneralException {

    public InvalidPageException(BaseErrorCode code) {
        super(code);
    }
}
