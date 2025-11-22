package org.sopt.web2.domain.user.controller.dto;

import org.sopt.web2.domain.user.entity.Language;

public record UserInfoResponse(
	Language nativeLanguage,
	Language targetLanguage,
	String job
) {
	public static UserInfoResponse of(Language nativeLanguage, Language targetLanguage, String job) {
		return new UserInfoResponse(nativeLanguage, targetLanguage, job);
	}
}
