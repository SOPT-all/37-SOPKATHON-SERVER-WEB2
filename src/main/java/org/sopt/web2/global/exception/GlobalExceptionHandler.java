package org.sopt.web2.global.exception;

import org.sopt.web2.global.response.CommonApiResponse;
import org.sopt.web2.global.response.code.BaseCode;
import org.sopt.web2.global.response.code.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<CommonApiResponse<?>> handleMyException(BusinessException e) {
		BaseCode errorCode = e.getBaseCode();
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(CommonApiResponse.fail(errorCode));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CommonApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return buildErrorResponse(ErrorCode.INVALID_FIELD_ERROR, e.getBindingResult());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<CommonApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e) {
		return buildErrorResponse(ErrorCode.INVALID_FIELD_ERROR, e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<CommonApiResponse<?>> handleMissingServletRequestParameterException(
		MissingServletRequestParameterException e) {
		return buildErrorResponse(ErrorCode.MISSING_PARAMETER, e.getParameterName());
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<CommonApiResponse<?>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		return buildErrorResponse(ErrorCode.MISSING_HEADER, e.getHeaderName());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<CommonApiResponse<?>> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
		String detail = e.getRequiredType() != null
			? String.format("'%s'은(는) %s 타입이어야 합니다.", e.getName(), e.getRequiredType().getSimpleName())
			: "타입 변환 오류입니다.";
		return buildErrorResponse(ErrorCode.TYPE_MISMATCH, detail);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CommonApiResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return buildErrorResponse(ErrorCode.INVALID_REQUEST_BODY, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonApiResponse<?>> handleException(Exception e) {
		log.error("❌예기치 않은 예외 발생 : ", e);
		CommonApiResponse<?> response = CommonApiResponse.fail(
			HttpStatus.INTERNAL_SERVER_ERROR,
			"서버 내부에서 문제가 발생하였습니다. exception: " + e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(response);
	}

	private ResponseEntity<CommonApiResponse<?>> buildErrorResponse(BaseCode errorCode, Object detail) {
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(CommonApiResponse.fail(errorCode, detail));
	}

}
