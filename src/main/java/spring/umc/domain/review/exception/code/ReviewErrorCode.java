package spring.umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    INVALID_REVIEW_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 요청이 올바르지 않습니다."),
    INVALID_STAR_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_2", "별점은 0.0~5.0 사이의 값이어야 합니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,  "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    DUPLICATE_REVIEW(HttpStatus.CONFLICT,   "REVIEW409_1", "이미 해당 가게에 리뷰를 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
