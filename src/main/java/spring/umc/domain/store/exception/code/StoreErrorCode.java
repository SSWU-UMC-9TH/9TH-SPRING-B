package spring.umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "ADDRESS404_1",
            "해당 가게가 존재하지 않습니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

