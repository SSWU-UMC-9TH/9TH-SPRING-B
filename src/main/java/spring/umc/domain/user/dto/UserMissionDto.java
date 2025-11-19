package spring.umc.domain.user.dto;

import lombok.Getter;
import spring.umc.domain.user.entity.MissionStatus;

@Getter
public class UserMissionDto {
    private MissionStatus status;
    private String missionName;
    private String storeName;

    public UserMissionDto(MissionStatus status, String missionName, String storeName) {
        this.status = status;
        this.missionName = missionName;
        this.storeName = storeName;
    }
}