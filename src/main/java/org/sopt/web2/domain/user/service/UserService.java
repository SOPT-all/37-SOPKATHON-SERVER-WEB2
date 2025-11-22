package org.sopt.web2.domain.user.service;

import org.sopt.web2.domain.user.controller.dto.UserInfoResponse;
import org.sopt.web2.domain.user.entity.User;
import org.sopt.web2.domain.user.exception.UserErrorCode;
import org.sopt.web2.domain.user.repository.UserRepository;
import org.sopt.web2.global.exception.BusinessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserInfoResponse getUserInfo(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(()-> new BusinessException(UserErrorCode.USER_NOT_FOUND));
		return UserInfoResponse.of(
			user.getNativeLanguage(),
			user.getTargetLanguage()
		//	user.getJob()
		);
	}
}
