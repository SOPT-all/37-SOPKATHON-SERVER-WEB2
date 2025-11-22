package org.sopt.web2.domain.matching.controller;

import org.sopt.web2.domain.matching.controller.dto.WishRequest;
import org.sopt.web2.domain.matching.controller.dto.WishResponse;

import org.sopt.web2.domain.matching.service.WishUserFacade;
import org.sopt.web2.global.response.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class WishController {

	private final WishUserFacade wishUserFacade;

	@PatchMapping("/onboarding")
	public ResponseEntity<CommonApiResponse<WishResponse>> patchMatchInfo(
		@RequestBody WishRequest request
	) {
		WishResponse response = wishUserFacade.saveWishAndPatchUser(request);
		return ResponseEntity.ok()
			.body(CommonApiResponse.success(HttpStatus.OK, "정보가 등록되었습니다.", response));
	}
}
