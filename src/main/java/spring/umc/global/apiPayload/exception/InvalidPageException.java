package spring.umc.global.apiPayload.exception;

import lombok.Getter;

@Getter
public class InvalidPageException extends RuntimeException {

    private final int page;

    public InvalidPageException(int page) {
        super("page 값이 1 이상이어야 합니다. 현재 값: " + page);
        this.page = page;
    }
}
