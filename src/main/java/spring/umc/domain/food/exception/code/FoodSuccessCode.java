package spring.umc.domain.food.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "FOOD200_1",
            "음식을 성공적으로 조회했습니다."),

    LIST_FOUND(HttpStatus.OK,
            "FOOD200_2",
            "음식 목록을 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}