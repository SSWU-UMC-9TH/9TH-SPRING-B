package spring.umc.domain.member.dto;

import spring.umc.domain.member.enums.Address;
import spring.umc.domain.member.enums.Sex;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO (
                    String name,
                    Sex sex,
                    LocalDate birth,
                    String nickname,
                    String password,
                    Address address,
                    String addressDetail,
                    @ExistFoods
                    List<Long> foodIds

    ){}
}
