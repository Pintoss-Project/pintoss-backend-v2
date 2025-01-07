package com.pintoss.gitftmall.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    BAD_REQUEST("4001", "잘못된 요청입니다."),
    INVALID_ACCESS("4002", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN("4003", "만료된 토큰입니다."),

    // 401 Unauthorized
    UNAUTHORIZED("4011", "잘못된 자격증명 입니다."),

    // 403 Forbidden
    DENIED_ACCESS("4031", "권한이 없습니다."),

    // 404 Not Found
    NOT_FOUND("4041", "자원을 찾을 수 없습니다."),

    // 409 Conflict
    CONFLICT("4091", "충돌이 발생했습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR("5001", "서버 내부 오류가 발생했습니다."),
    CREATION_FAILURE("5002", "생성에 실패했습니다."),
    UPDATE_FAILURE("5003", "업데이트에 실패했습니다."),
    DELETION_FAILURE("5004", "삭제에 실패했습니다."),
    IMAGE_UPLOAD_ERROR("5005", "이미지 업로드에 실패했습니다.");

    private final String code;
    private final String message;
}
