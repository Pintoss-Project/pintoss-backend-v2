package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.controller.request.UserInfoResponse;
import com.pintoss.gitftmall.domain.membership.infra.repository.UserRepositoryImpl;
import com.pintoss.gitftmall.domain.membership.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserInfoService {

    private final UserRepositoryImpl userRepository;

    public List<UserInfoResponse> findUserInfo(Pageable pageable) {
        Page<User> userList = userRepository.getUsers(pageable);

        return userList.stream()
                .map(user -> new UserInfoResponse(
                        user.getEmail().toString(),
                        user.getName(),
                        user.getPhone().toString()
                ))
                .toList();
    }
}
