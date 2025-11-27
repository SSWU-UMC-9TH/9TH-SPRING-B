package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.global.apiPayload.ApiResponse;

public interface MissionControllerDocs {

    // 내가 진행 중인 미션 목록 API (page 기반 페이징)
    @Operation(
            summary = "내가 진행 중인 미션 목록 조회 API By 이루 (개발 중)",
            description = "내가 진행 중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.OngoingListDTO> getOngoingMissionsByPage(
            Long memberId,
            Integer page
    );


    // 특정 가게의 미션 목록 API
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 이루 (개발 중)",
            description = "해당 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            Long storeId,
            Integer page
    );

    // 진행 중인 미션 진행 완료로 바꾸기 API
    @Operation(
            summary = "진행 중 미션 완료로 처리 API By 이루 (개발 중)",
            description = "진행 중인 미션을 완료 상태로 변경하고, 변경된 미션 정보를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.CompletedItem> completeMission(
            Long memberId,
            Long missionId
    );
}
