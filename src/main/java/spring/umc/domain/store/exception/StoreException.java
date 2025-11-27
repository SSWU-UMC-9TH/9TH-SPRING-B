package spring.umc.domain.store.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
