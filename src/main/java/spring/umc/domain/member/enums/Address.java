package spring.umc.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Address {

    GANGNAMGU("강남구"),
    GANGDONGGU("강동구"),
    GANGBUKGU("강북구"),
    GANGSEOGU("강서구"),
    GWANAKGU("관악구"),
    GWANGJINGU("광진구"),
    GUROGU("구로구"),
    GEUMCHEONGU("금천구"),
    NOWONGU("노원구"),
    DOBONGGU("도봉구"),
    DONGDAEMUNGU("동대문구"),
    DONGJAKGU("동작구"),
    MAPOGU("마포구"),
    SEODAEMUNGU("서대문구"),
    SEOCHOGU("서초구"),
    SEONGDONGGU("성동구"),
    SEONGBUKGU("성북구"),
    SONGPAGU("송파구"),
    YANGCHEONGU("양천구"),
    YEONGDEUNGPOGU("영등포구"),
    YONGSANGU("용산구"),
    EUNPYEONGGU("은평구"),
    JONGNOGU("종로구"),
    JUNGGU("중구"),
    JUNGNANGGU("중랑구");

    private final String koreanName;
}
