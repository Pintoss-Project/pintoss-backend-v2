package com.pintoss.gitftmall.infra.security;

import com.pintoss.gitftmall.domain.membership.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomDetails implements UserDetails {

    private User user;
    private String accessToken;
    private String refreshToken;

    public CustomDetails(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // 계정 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return user.getPassword().getPassword();
    }

    // 이메일 반환
    public String getEmail(){
        return user.getEmail().getEmail();
    }
    // 아이디 반환
    public Long getId() { return user.getId(); }

    // 이메일 반환
    @Override
    public String getUsername() {
        return String.valueOf(user.getId());
    }
}

