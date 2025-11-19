package spring.umc.domain.auth.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.auth.converter.AuthConverter;
import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;
import spring.umc.domain.store.entity.FoodCategory;
import spring.umc.domain.store.exception.FoodException;
import spring.umc.domain.store.exception.code.FoodErrorCode;
import spring.umc.domain.store.repository.FoodCategoryRepository;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.user.entity.UserFood;
import spring.umc.domain.user.repository.UserFoodRepository;
import spring.umc.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    // 회원가입
    @Override
    public AuthResDto.JoinDto signUp(AuthReqDto.JoinDto dto) {
        // 사용자 생성
        User user = AuthConverter.toUser(dto);
        // DB 적용
        userRepository.save(user);

        // 선호 음식 존재 여부 확인
        if (dto.userCategoryIds() != null && !dto.userCategoryIds().isEmpty()){
            List<UserFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            // stream()을 통해 구현할 수 있으며, stream이 더 빠름 (성능 향상)
            for (Long id : dto.userCategoryIds()){

                // 음식 존재 여부 검증
                FoodCategory food = foodCategoryRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // UserFood 엔티티 생성 (컨버터 사용해야 함)
                UserFood userFood = UserFood.builder()
                        .user(user)
                        .foodCategory(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(userFood);
            }

            // 모든 선호 음식 추가: DB 적용
            userFoodRepository.saveAll(memberFoodList);
        }


        // 응답 DTO 생성
        return AuthConverter.toJoinDto(user);
    }
}
