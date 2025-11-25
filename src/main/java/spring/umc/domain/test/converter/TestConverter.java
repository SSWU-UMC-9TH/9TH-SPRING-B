package spring.umc.domain.test.converter;

import spring.umc.domain.test.dto.res.TestResDto;

public class TestConverter {

    // 객체 -> DTO
    public static TestResDto.Testing toTestingDto(
            String testing
    ){
        return TestResDto.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResDto.Exception toExceptionDto(
            String testing
    ){
        return TestResDto.Exception.builder()
                .testString(testing)
                .build();
    }
}
