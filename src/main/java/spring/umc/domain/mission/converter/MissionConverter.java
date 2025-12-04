package spring.umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.MissionResponseDto;
import spring.umc.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResponseDto.MissionListDto toMissionListDto(Page<Mission> missionPage) {

        return MissionResponseDto.MissionListDto.builder()
                .missionList(
                        missionPage.getContent().stream()
                                .map(MissionConverter::toMissionDto)
                                .toList()
                )
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResponseDto.MissionDto toMissionDto(Mission mission) {
        return MissionResponseDto.MissionDto.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())
                .build();
    }
}
