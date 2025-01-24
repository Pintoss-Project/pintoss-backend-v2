package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.infra.config.security.authentication.CustomAuthentication;
import com.pintoss.gitftmall.infra.config.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginResult login(LoginServiceCommand command) {
        Authentication authenticate = authenticationManager.authenticate(
            new CustomAuthentication(command.getEmail(), command
                .getPassword()));
        CustomUserDetails principal = (CustomUserDetails) authenticate.getPrincipal();
        LoginResult loginResult = new LoginResult(principal.getAccessToken(), principal.getRefreshToken());
        return loginResult;
    }
}
