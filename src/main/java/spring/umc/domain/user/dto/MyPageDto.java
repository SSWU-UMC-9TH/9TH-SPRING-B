package spring.umc.domain.user.dto;

import lombok.Getter;

@Getter
public class MyPageDto {
    private String nickname;
    private String email;
    private String phoneNumber;
    private Boolean phoneVerified;
    private Integer point;

    public MyPageDto(String nickname, String email, String phoneNumber, Boolean phoneVerified, Integer point) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.phoneVerified = phoneVerified;
        this.point = point;
    }
}
