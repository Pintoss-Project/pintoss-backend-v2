package com.pintoss.gitftmall.infra.security.provider;

import com.pintoss.gitftmall.infra.security.model.CustomDetails;
import com.pintoss.gitftmall.infra.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        // 사용자 이름 검증
        CustomDetails userDetails;
        try {
            userDetails = (CustomDetails) userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        // 비밀번호 검증
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 계정 활성화 확인
        if (!userDetails.isEnabled()) {
            throw new DisabledException("계정이 비활성화되었습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
