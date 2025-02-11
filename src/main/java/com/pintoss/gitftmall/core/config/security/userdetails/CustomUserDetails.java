package com.pintoss.gitftmall.core.config.security.userdetails;

import com.pintoss.gitftmall.domain.membership.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private User user;
    private String accessToken;
    private String refreshToken;

    public CustomUserDetails(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    // 계정 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role.getName().toString();
                    }
                }).collect(Collectors.toSet());
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

