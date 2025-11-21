package org.sopt.web2.global.response.code;

import org.springframework.http.HttpStatus;

public interface BaseCode {
	HttpStatus getHttpStatus();
	String getMessage();
}
