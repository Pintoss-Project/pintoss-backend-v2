package com.pintoss.gitftmall.core.config.security.authentication;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.UserRole;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private User user;

    public CustomAuthentication(Object principal, Object credentials, User user) {
        super(principal, credentials, authorities(user.getRoles()));
        this.user = user;
    }
    public CustomAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
        this.user = null;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getName(){
        return String.valueOf(user.getEmail().getEmail());
    }
}
