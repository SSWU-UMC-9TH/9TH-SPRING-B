package spring.umc.domain.store.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
