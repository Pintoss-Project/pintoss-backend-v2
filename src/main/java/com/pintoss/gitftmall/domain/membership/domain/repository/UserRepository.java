package com.pintoss.gitftmall.domain.membership.domain.repository;

import com.pintoss.gitftmall.domain.membership.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail_Email(String email);

    Optional<User> findById(long id);

    Optional<User> findByIdWithRoles(long id);

    boolean existsByPhone_Phone(String phone);

    Page<User> getUsers(Pageable pageable);
}
