package com.pintoss.gitftmall.infra.security.service;

import com.pintoss.gitftmall.common.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import com.pintoss.gitftmall.infra.security.model.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final IUserRepository userRepository;

    @Autowired
    public SecurityServiceImpl(IUserRepository userRepository) {
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
