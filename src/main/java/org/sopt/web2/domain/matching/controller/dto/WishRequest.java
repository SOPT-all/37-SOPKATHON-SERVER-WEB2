package org.sopt.web2.domain.matching.controller.dto;

import org.sopt.web2.domain.user.entity.Language;

public record WishRequest(
	Language nativeLanguage,
	Language targetLanguage,
	String location,
	String timeSlot,
	String job
) {
}
