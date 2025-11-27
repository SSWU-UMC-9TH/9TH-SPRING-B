package spring.umc.global.apiPayload.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.exception.InvalidPageException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPageException.class)
    public ApiResponse<?> handleInvalidPage(InvalidPageException e) {
        return ApiResponse.onFailure(GeneralErrorCode.INVALID_PAGE, null);
    }
}