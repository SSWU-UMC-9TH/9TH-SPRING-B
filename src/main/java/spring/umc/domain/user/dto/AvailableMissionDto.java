package spring.umc.domain.user.dto;

import spring.umc.domain.user.entity.MissionStatus;

public class AvailableMissionDto {
    private Long missionId;
    private String missionName;
    private String storeName;
    private MissionStatus status; // UserMission의 상태 Enum

    public AvailableMissionDto(Long missionId, String missionName, String storeName, MissionStatus status) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.storeName = storeName;
        this.status = status;
    }
}
