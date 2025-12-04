package spring.umc.global.annotation.paging;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import spring.umc.global.apiPayload.exception.InvalidPageException;

public class PageParamArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String PAGE_PARAM_NAME = "page";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageParam.class)
                && (parameter.getParameterType().equals(Integer.class)
                || parameter.getParameterType().equals(int.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String pageStr = request.getParameter(PAGE_PARAM_NAME);

        int page = (pageStr == null || pageStr.isBlank())
                ? 1        // 기본값 1
                : Integer.parseInt(pageStr);

        if (page <= 0) {
            throw new InvalidPageException(page);
        }

        // 프론트는 1부터, Spring Data JPA 는 0부터 → 여기서 -1 처리
        return page - 1;
    }
}