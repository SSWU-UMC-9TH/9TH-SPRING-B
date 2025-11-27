package spring.umc.domain.auth.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException {

    public AuthException(BaseErrorCode code) {
        super(code);
    }
}
