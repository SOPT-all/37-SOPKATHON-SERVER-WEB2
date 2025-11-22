package org.sopt.web2.domain.matching.service;

import org.sopt.web2.domain.matching.controller.dto.WishRequest;
import org.sopt.web2.domain.matching.controller.dto.WishResponse;
import org.sopt.web2.domain.matching.entity.Wish;
import org.sopt.web2.domain.matching.repository.WishRepository;

import org.sopt.web2.domain.user.entity.User;
import org.sopt.web2.domain.user.exception.UserErrorCode;
import org.sopt.web2.domain.user.repository.UserRepository;
import org.sopt.web2.global.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WishUserFacade {

	private final WishRepository wishRepository;
	private final UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(()-> new BusinessException(UserErrorCode.USER_NOT_FOUND));
	}

	@Transactional
	public WishResponse saveWishAndPatchUser(WishRequest request) {
		Wish wish = wishRepository.save(new Wish(findById(1L), request.location(), request.timeSlot(), request.job()));

		userRepository.patchUserByUserId(1L, request.nativeLanguage(), request.targetLanguage());
		return WishResponse.of(wish.getId(), wish.getLocation(), wish.getTimeSlot());
	}

}
