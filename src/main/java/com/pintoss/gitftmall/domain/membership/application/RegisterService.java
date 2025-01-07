package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.common.exceptions.client.DuplicateEmailException;
import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.*;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    public void signup(RegisterServiceCommand command) {
        if(userRepository.existsByEmail_Email(command.getEmail())){
            throw new DuplicateEmailException("이미 존재하는 회원입니다.");
        };

        User user = User.create(
                new Email(command.getEmail()),
                command.getPassword(),
                command.getName(),
                new Phone(command.getPhone()),
                Set.of(new UserRole(RoleEnum.USER)),
                encoder
        );

        userRepository.save(user);
    }

}
