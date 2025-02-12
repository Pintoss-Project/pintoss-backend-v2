package com.pintoss.gitftmall.core.security.authentication;

import com.pintoss.gitftmall.core.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthenticationProviderImpl implements SecurityAuthenticationProvider {

    private final UserRepository userRepository;

    @Autowired
    public SecurityAuthenticationProviderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication getAuthentication(String memberId) {
        User user = getUser(memberId);

        return createAuthentication(user);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(User user) {
        return new CustomAuthentication(user.getEmail().getEmail(), user.getPassword().getPassword(), user);
    }

    private User getUser(String userId) {
        long id = Long.parseLong(userId);

        return userRepository.findByIdWithRoles(id)
                .orElseThrow(() -> new NotFoundMemberException("존재하지 않는 회원입니다."));
    }
}
