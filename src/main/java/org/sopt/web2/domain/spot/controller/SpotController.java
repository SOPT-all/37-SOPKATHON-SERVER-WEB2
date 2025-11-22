package org.sopt.web2.domain.spot.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.spot.controller.dto.SpotResponse;
import org.sopt.web2.domain.spot.service.SpotService;
import org.sopt.web2.global.response.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spot")
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    public ResponseEntity<CommonApiResponse<List<SpotResponse>>> getAllProducts(
            @RequestParam(required = false, defaultValue = "default") String searchWord
    ) {
        List<SpotResponse> spots = spotService.getSelectedSpots(searchWord);
        return ResponseEntity.ok(CommonApiResponse.success(HttpStatus.OK, "검색 결과가 조회되었습니다.",spots));
    }

}
