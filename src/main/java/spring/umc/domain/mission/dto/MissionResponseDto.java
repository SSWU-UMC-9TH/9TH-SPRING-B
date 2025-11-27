package spring.umc.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.user.entity.UserMission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionResponseDto {

    @Builder
    public record Challenge(
            Long userMissionId
    ) {}

    @Getter
    @Builder
    public static class StoreMissionDto {
        private Long missionId;
        private String missionName;
        private String type;
        private LocalDateTime deadline;
        private Long storeId;
        private String storeName;
    }

    @Getter
    @Builder
    public static class MyMissionDto {
        private Long userMissionId;
        private Long missionId;
        private String missionName;
        private String storeName;
        private String status;
        private LocalDateTime deadline;
    }

    // Converter
    public static StoreMissionDto toStoreMissionDto(Mission mission) {
        return StoreMissionDto.builder()
                .missionId(mission.getId())
                .missionName(mission.getName())
                .type(mission.getType())
                .deadline(mission.getDeadline())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .build();
    }

    public static List<StoreMissionDto> toStoreMissionDtoList(Page<Mission> page) {
        return page.getContent().stream()
                .map(MissionResponseDto::toStoreMissionDto)
                .collect(Collectors.toList());
    }

    public static MyMissionDto toMyMissionDto(UserMission userMission) {
        Mission mission = userMission.getMission();
        return MyMissionDto.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .missionName(mission.getName())
                .storeName(mission.getStore().getName())
                .status(userMission.getStatus().name())
                .deadline(mission.getDeadline())
                .build();
    }

    public static List<MyMissionDto> toMyMissionDtoList(Page<UserMission> page) {
        return page.getContent().stream()
                .map(MissionResponseDto::toMyMissionDto)
                .collect(Collectors.toList());
    }
}