package com.pintoss.gitftmall.domain.membership.domain.vo;

import com.pintoss.gitftmall.core.exceptions.server.InternalServerException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    PENDING("pending"),
    ACTIVE("active"),
    DELETED("deleted");

    private final String value;

    public UserStatus fromValue(String value) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.value.equalsIgnoreCase(value)) {
                return userStatus;
            }
        }
        throw new InternalServerException("지원하지 않는 사용자 상태입니다.");
    }
}
