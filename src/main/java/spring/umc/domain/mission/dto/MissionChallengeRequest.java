package spring.umc.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissionChallengeRequest {

    @NotNull
    private Long missionId;

    @NotNull
    private Long memberId;
}
