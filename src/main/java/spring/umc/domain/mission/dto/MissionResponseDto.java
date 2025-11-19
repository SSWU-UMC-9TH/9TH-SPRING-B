package spring.umc.domain.mission.dto;

import lombok.Builder;

public class MissionResponseDto {

    @Builder
    public record Challenge(
            Long userMissionId
    ) {}
}