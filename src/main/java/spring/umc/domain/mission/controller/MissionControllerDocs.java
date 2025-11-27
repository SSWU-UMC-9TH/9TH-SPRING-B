package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.umc.domain.mission.dto.res.MissionResDTO;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.ApiResponse;

public interface MissionControllerDocs {

    // 특정 가게의 미션 목록
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 이루 (개발 중)",
            description = "해당 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            Long storeId,
            Integer page
    );
}
