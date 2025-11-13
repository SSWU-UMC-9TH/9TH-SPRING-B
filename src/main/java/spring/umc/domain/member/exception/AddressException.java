package spring.umc.domain.member.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class AddressException extends GeneralException {
    public AddressException(BaseErrorCode code) {
        super(code);
    }
}
