package spring.umc.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import spring.umc.domain.user.entity.Gender;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDto {

    public record JoinDto(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
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

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}

}
