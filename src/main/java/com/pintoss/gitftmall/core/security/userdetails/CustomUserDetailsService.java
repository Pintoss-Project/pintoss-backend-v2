package com.pintoss.gitftmall.core.security.userdetails;

import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.IUserRepository;
import com.pintoss.gitftmall.core.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다"));

        String accessToken = tokenProvider.createAccessToken(String.valueOf(user.getId()));
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(user.getId()));

        user.storeRefreshToken(refreshToken);

        return new CustomUserDetails(user, accessToken, refreshToken);
    }
}

