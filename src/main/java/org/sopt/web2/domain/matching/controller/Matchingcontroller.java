package org.sopt.web2.domain.matching.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.matching.controller.dto.MatchingResponse;
import org.sopt.web2.domain.matching.service.MatchingService;
import org.sopt.web2.global.response.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matching")
public class Matchingcontroller {

    private final MatchingService matchingService;

    @GetMapping("/{wishId}")
    public ResponseEntity<CommonApiResponse<MatchingResponse>> getMatching(
            @PathVariable Long wishId
    ) {
        MatchingResponse response = matchingService.getMatching(wishId);
        return ResponseEntity.ok(
                CommonApiResponse.success(HttpStatus.OK, "매칭 상대의 정보가 조회되었습니다.", response)
        );
    }
}
