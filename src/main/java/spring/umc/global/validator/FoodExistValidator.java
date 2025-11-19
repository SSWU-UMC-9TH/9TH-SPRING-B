package spring.umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.umc.domain.store.exception.code.FoodErrorCode;
import spring.umc.domain.user.repository.UserFoodRepository;
import spring.umc.global.annotation.ExistFoods;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final UserFoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()   // stream을 돌려서 DB에 없는 경우, isValid를 false로 변경
                .allMatch(value -> foodRepository.existsById(value));

        // if문에서 검증에 실패할 경우 FoodErrorCode의 NOT_FOUND 메시지를 원인으로 반환
        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}
