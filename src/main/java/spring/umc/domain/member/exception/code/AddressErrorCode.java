package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum AddressErrorCode implements BaseErrorCode {

    ADDRESS404_1(
            HttpStatus.NOT_FOUND,
            "ADDRESS404_1",
            "해당 회원의 주소가 없습니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
