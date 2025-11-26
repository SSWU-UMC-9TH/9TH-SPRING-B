package spring.umc.domain.store.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum RegionName {
    SEOUL("서울특별시"),
    INCHEON("인천광역시"),
    SUWON("수원시"),
    SEONGNAM("성남시"),
    GOYANG("고양시"),
    YONGIN("용인시"),
    BUCHEON("부천시"),
    ANSAN("안산시"),
    ANYANG("안양시"),
    NAMYANGJU("남양주시"),
    UIJEONGBU("의정부시"),
    PAJU("파주시"),
    SIHEUNG("시흥시"),
    GIMPO("김포시"),
    GWANGMYEONG("광명시"),
    HANAM("하남시"),
    OSAN("오산시"),
    GUNPO("군포시"),
    UIWANG("의왕시"),
    YANGJU("양주시"),
    ICHEON("이천시"),
    PYEONGTAEK("평택시"),
    GWANGJU_GYEONGGI("광주시"),
    ANSEONG("안성시"),
    DONGDUCHEON("동두천시"),
    GAPYEONG("가평군"),
    YEOJU("여주시"),
    YANGPYEONG("양평군"),
    POCHON("포천시");

    private final String koreanName;
}
