package spring.umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDto {

    @Builder
    public record MissionListDto(
            List<MissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionDto(
            Long missionId,
            Long point,
            String condition,
            LocalDateTime deadline,
            String storeName
    ) {}
}
