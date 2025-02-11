package com.pintoss.gitftmall.core.util;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.core.config.security.authentication.CustomAuthentication;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityContextUtils {

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static Long getUserId() {
        User user = getUser();
        return user == null ? null : user.getId();
    }

    public static User getUser() {
        return SecurityContextHolder.getContext().getAuthentication() != null ?
                ((CustomAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUser() :
                null;
    }
}
