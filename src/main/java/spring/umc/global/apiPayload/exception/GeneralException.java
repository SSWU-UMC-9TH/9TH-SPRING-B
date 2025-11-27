package spring.umc.global.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}
