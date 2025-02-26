package com.pintoss.gitftmall;

import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import com.pintoss.gitftmall.domain.membership.domain.vo.Email;
import com.pintoss.gitftmall.domain.membership.domain.vo.Phone;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.membership.domain.vo.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootTest
class GitftmallApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Test
    void contextLoads() {
    }

    @Test
    void createUser() {
        Email email = new Email("test@naver.com");
        User user = User.create(email, "test1234!","테스트유저",new Phone("010-1111-1111"), Set.of(new UserRole(RoleEnum.USER)) ,encoder);
        userRepository.save(user);
    }

}
