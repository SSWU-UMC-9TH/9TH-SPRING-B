package spring.umc.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import spring.umc.domain.member.enums.Address;
import spring.umc.domain.member.enums.Sex;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO (
            @NotBlank
            String name,
            @NotNull
            Sex sex,
            @NotNull
            LocalDate birth,
            @NotNull
            String nickname,
            @NotNull
            String password,
            @NotNull
            Address address,
            @NotNull
            String addressDetail,
            @Email
            String email,
            @ExistFoods
            List<Long> foodIds

    ){}


        // 로그인
        public record LoginDTO(
                @NotBlank
                String email,
                @NotBlank
                String password
        ){}
}
