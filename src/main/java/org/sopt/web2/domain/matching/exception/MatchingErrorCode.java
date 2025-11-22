package org.sopt.web2.domain.matching.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.web2.global.response.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MatchingErrorCode implements BaseCode {

    MATCHING_NOT_FOUND(HttpStatus.NOT_FOUND, "매칭 정보를 찾을 수 없습니다."),
    WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "희망 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
