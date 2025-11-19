package spring.umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.umc.global.validator.FoodExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션 만듦
@Constraint(validatedBy = FoodExistValidator.class) // 커스텀 어노테이션을 통해 validation을 할 수 있도록 제공
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) // 어노테이션 적용 범위
@Retention(RetentionPolicy.RUNTIME) // 생명 주기 지정
public @interface ExistFoods {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}