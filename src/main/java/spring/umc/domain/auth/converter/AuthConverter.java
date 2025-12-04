package spring.umc.domain.auth.converter;

import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;
import spring.umc.domain.user.entity.Role;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.user.entity.UserAddress;

public class AuthConverter {

    // Entity -> DTO
    public static AuthResDto.JoinDto toJoinDto(User user) {
        return AuthResDto.JoinDto.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())   // createAt → createdAt 수정
                .build();
    }

    // DTO -> Entity
    public static User toUser(
            AuthReqDto.JoinDto dto,
            String password,
            Role role
    ){

        // 1) 우선 User 자체 생성
        User user = User.builder()
                .nickname(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)         // 추가된 코드
                .gender(dto.gender())
                .birth(dto.birth())
                .build();

        // 2) 주소 DTO가 있으면 UserAddress 엔티티로 변환 후 연관관계 세팅
        if (dto.address() != null) {
            AuthReqDto.AddressDto addr = dto.address();

            UserAddress userAddress = new UserAddress(
                    addr.postalCode(),
                    addr.baseAddress(),
                    addr.detailAddress()
            );

            user.updateAddress(userAddress); // 양방향 연관관계 설정
        }

        return user;
    }

    public static AuthResDto.LoginDTO toLoginDTO(User user, String accessToken) {
        return AuthResDto.LoginDTO.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }

}
