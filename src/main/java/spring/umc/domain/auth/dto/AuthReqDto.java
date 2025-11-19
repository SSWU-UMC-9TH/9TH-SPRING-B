package spring.umc.domain.auth.dto;

import spring.umc.domain.user.entity.Gender;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDto {

    public record JoinDto(
            String name,
            Gender gender,
            LocalDate birth,
            AddressDto address,
            @ExistFoods
            List<Long> userCategoryIds
    ){}

    // 요청용 주소 DTO
    public record AddressDto(
            String postalCode,
            String baseAddress,
            String detailAddress
    ) {}

}
