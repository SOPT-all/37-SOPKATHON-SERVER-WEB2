package org.sopt.web2.domain.user.service;

import org.sopt.web2.domain.matching.entity.Wish;
import org.sopt.web2.domain.matching.exception.WishErrorCode;
import org.sopt.web2.domain.matching.repository.WishRepository;
import org.sopt.web2.domain.user.controller.dto.UserInfoResponse;
import org.sopt.web2.domain.user.entity.User;
import org.sopt.web2.domain.user.exception.UserErrorCode;
import org.sopt.web2.domain.user.repository.UserRepository;
import org.sopt.web2.global.exception.BusinessException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserWishFacade {

	private final WishRepository wishRepository;
	private final UserRepository userRepository;

	public UserInfoResponse getUserInfo(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(()-> new BusinessException(UserErrorCode.USER_NOT_FOUND));

		return UserInfoResponse.of(
			user.getNativeLanguage(),
			user.getTargetLanguage(),
			getLastWishJob(userId)
		);
	}

	private String getLastWishJob(Long userId) {
		Wish wish = wishRepository.findTopByUserIdOrderByIdDesc(userId)
			.orElseThrow(()-> new BusinessException(WishErrorCode.WISH_NOT_FOUND));

		return wish.getJob();
	}
}
