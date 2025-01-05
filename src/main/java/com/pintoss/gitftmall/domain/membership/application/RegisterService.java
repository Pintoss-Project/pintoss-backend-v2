package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    public void signup(RegisterServiceCommand command) {
        if(userRepository.existsByEmail(command.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        };

        encoder.encode(command.getPassword());
        User user = User.create(command.getEmail(), command.getPassword(), command.getName(), command.getPhone());

        userRepository.save(user);
    }

}
