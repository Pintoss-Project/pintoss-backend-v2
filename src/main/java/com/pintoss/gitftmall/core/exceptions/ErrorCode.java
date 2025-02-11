package com.pintoss.gitftmall.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    BAD_REQUEST("4001", "잘못된 요청입니다."),
    INVALID_ACCESS("4002", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN("4003", "만료된 토큰입니다."),
    MISSING_REQUEST_FIELD("4004", "필수 입력 필드가 누락되었습니다."),
    INVALID_REQUEST_FIELD("4005", "잘못된 요청 필드입니다."),
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
    IMAGE_UPLOAD_ERROR("5005", "이미지 업로드에 실패했습니다."),
    UNSUPPORTED_ENCRYPTION_ALGORITHM("5006", "지원하지 않는 암호화 알고리즘입니다."),
    INVALID_HMAC_KEY("5007", "잘못된 HMAC 키입니다."),
    UNSUPPORTED_PADDING_SCHEMA("5008", "지원하지 않는 패딩스킴(PKCS5Padding)입니다."),
    INVALID_ENCRYPTION_KEY("5009", "암호화 키가 잘못되었거나 길이가 적합하지 않습니다."),
    INVALID_ALGORITHM_PARAMETER("50010", "초기화 벡터(IV)가 잘못되었거나 형식이 맞지 않습니다."),
    JSON_SERIALIZATION("50011", "요청 데이터를 JSON 형식으로 변환할 수 없습니다."),
    ILLEGAL_BLOCK_SIZE("50012", "암호화 시 블록 크기가 부적합합니다."),
    BAD_PADDING("50013", "패딩이 잘못되었거나 데이터 손상이 의심됩니다.");

    private final String code;
    private final String message;
}
