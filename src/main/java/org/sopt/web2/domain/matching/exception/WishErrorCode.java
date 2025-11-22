package org.sopt.web2.domain.matching.exception;

import org.sopt.web2.global.response.code.BaseCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WishErrorCode implements BaseCode {

	WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
