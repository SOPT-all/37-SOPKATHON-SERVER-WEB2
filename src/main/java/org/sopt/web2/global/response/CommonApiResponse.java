package org.sopt.web2.global.response;

import org.sopt.web2.global.response.code.BaseCode;
import org.springframework.http.HttpStatus;

public record CommonApiResponse<T> (
	int status,
	String message,
	T data
) {
	//성공 응답, 반환 데이터 없음
	public static <T> CommonApiResponse<T> success(BaseCode basecode) {
		return new CommonApiResponse<>(basecode.getHttpStatus().value(), basecode.getMessage(), null);
	}

	//성공 응답, 반환 데이터 있음
	public static <T> CommonApiResponse<T> success(BaseCode basecode, T data) {
		return new CommonApiResponse<>(basecode.getHttpStatus().value(), basecode.getMessage(), data);
	}

	//성공 응답, HttpStatus 직접 받음, message 직접 받음, 반환 데이터 있음
	public static <T> CommonApiResponse<T> success(HttpStatus httpStatus, String message, T data) {
		return new CommonApiResponse<>(httpStatus.value(), message, data);
	}

	//실패 응답, 반환 데이터 없음
	public static <T> CommonApiResponse<T> fail(BaseCode basecode) {
		return new CommonApiResponse<>(basecode.getHttpStatus().value(), basecode.getMessage(), null);
	}

	//실패 응답, 반환 데이터 없음, 메시지 커스텀 (예기치 않은 오류 핸들링 용)
	public static <T> CommonApiResponse<T> fail(HttpStatus httpStatus, String message) {
		return new CommonApiResponse<>(httpStatus.value(), message, null);
	}

	//실패 응답, 디테일 추가
	public static <T> CommonApiResponse<T> fail(BaseCode baseCode, Object detail) { //디테일 추가 커스텀
		return new CommonApiResponse<>(baseCode.getHttpStatus().value(),
			baseCode.getMessage() + (detail != null ? ": " + detail : ""), null
		);
	}

}
