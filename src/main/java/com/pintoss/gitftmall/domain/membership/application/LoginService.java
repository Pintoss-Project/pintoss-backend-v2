package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.infra.security.model.CustomAuthentication;
import com.pintoss.gitftmall.infra.security.model.CustomDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginResult login(LoginServiceCommand command) {
        // TODO autnenticationManager.authenticate에서 에러남. 현재 command에 email과 pw는 잘 넘어오는것 확인.
        System.out.println("command = " + command);
        Authentication authenticate = authenticationManager.authenticate(
            new CustomAuthentication(command.getEmail(), command
                .getPassword()));
        System.out.println("authenticate = " + authenticate);
        CustomDetails principal = (CustomDetails) authenticate.getPrincipal();
        LoginResult loginResult = new LoginResult(principal.getAccessToken(), principal.getRefreshToken());
        return loginResult;
    }
}
