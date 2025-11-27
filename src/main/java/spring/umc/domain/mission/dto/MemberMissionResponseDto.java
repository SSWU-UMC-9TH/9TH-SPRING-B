package spring.umc.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDto {

    @Builder
    public record MemberMissionListDto(
            List<MemberMissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MemberMissionDto(
            Long missionId,
            String missionCondition,
            Long point,
            LocalDateTime deadline,
            String storeName
    ) {}
}
