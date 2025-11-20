package spring.umc.domain.mission.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissionChallengeResponse {
    private Long challengeId;
    private Long memberId;
    private Long missionId;
    private Boolean isComplete;
}
