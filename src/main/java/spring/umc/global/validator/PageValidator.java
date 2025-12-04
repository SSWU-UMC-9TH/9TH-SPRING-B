package spring.umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.code.PageErrorCode;

import javax.naming.Context;

@Component
@RequestMapping
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null) return true;

        boolean isValid = value >= 1;

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    PageErrorCode.PAGE_LESS_THAN_ONE.getMessage()
            ).addConstraintViolation();
        }

        return isValid;
    }

}
