package spring.umc.domain.member.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.enums.Gender;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record AddressDTO(
            @NotBlank String postalCode,
            @NotBlank String roadAddress,
            String addressDetail,
            @NotBlank String fullAddress,
            @NotBlank String legalDong,
            @NotBlank String legalDongCode
    ){}

    // 회원가입
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            AddressDTO address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
