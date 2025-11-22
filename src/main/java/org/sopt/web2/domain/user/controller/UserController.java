package org.sopt.web2.domain.user.controller;

import org.sopt.web2.domain.user.controller.dto.UserInfoResponse;
import org.sopt.web2.domain.user.service.UserService;
import org.sopt.web2.global.response.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/onboarding")
	public ResponseEntity<CommonApiResponse<UserInfoResponse>> getUserInfo() {
		UserInfoResponse response = userService.getUserInfo(1L);
		return ResponseEntity.ok()
			.body(CommonApiResponse.success(HttpStatus.OK, "정보 작성 기록이 조회되었습니다.", response));
	}

}
