package spring.umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.MemberMissionResponseDto;
import spring.umc.domain.mission.entity.MemberMission;

public class MemberMissionConverter {

    public static MemberMissionResponseDto.MemberMissionListDto toMemberMissionListDto(
            Page<MemberMission> missionPage
    ) {

        return MemberMissionResponseDto.MemberMissionListDto.builder()
                .missionList(
                        missionPage.getContent().stream()
                                .map(MemberMissionConverter::toMemberMissionDto)
                                .toList()
                )
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionDto toMemberMissionDto(
            MemberMission memberMission
    ) {
        return MemberMissionResponseDto.MemberMissionDto.builder()
                .missionId(memberMission.getMission().getId())
                .missionCondition(memberMission.getMission().getCondition())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }
}
