package spring.umc.global.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import spring.umc.global.exception.InvalidPageException;
import spring.umc.global.exception.code.PageErrorCode;

@Component
@RequiredArgsConstructor
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {

        String pageStr = webRequest.getParameter("page");

        // page 파라미터 아예 안 들어오면 default = 1
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        if (page < 1) {
            throw new InvalidPageException(PageErrorCode.INVALID_PAGE);
        }

        // page(1부터 시작)을 Spring 기준(0부터 시작)으로 변환
        return PageRequest.of(page - 1, 10);
    }
}
