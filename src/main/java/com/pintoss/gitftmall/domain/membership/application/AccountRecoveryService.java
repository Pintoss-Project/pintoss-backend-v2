package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.core.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import com.pintoss.gitftmall.domain.membership.domain.vo.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRecoveryService {

    private final UserRepository userRepository;

    public String findAccount(String name, String phone) {
        User findUser = userRepository.findByNameAndPhone(name, new Phone(phone)).orElseThrow(
                () -> new NotFoundMemberException("존재하지 않는 회원입니다.")
        );
        return findUser.getEmail().getEmail();
    }
}
