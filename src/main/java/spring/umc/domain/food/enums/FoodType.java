package spring.umc.domain.food.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodType {

    KOREAN("한식"),
    CHINESE("중식"),
    WESTERN("양식"),
    JAPANESE("일식"),
    SNACK("분식"),
    FASTFOOD("패스트푸드"),
    DESSERT("디저트"),
    CAFE("카페"),
    PUB("주점"),
    ETC("기타");

    private final String koreanName;
}
