package spring.umc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponse {
    private String name;
    private String email;
    private String phone;
    private boolean phoneVerified;
    private int point;
}
