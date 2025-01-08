package com.pintoss.gitftmall.domain.membership.repository;

import com.pintoss.gitftmall.domain.membership.model.User;
import java.util.Optional;

public interface IUserRepository {

    Long save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail_Email(String email);

    Optional<User> findById(long id);

    Optional<User> findByIdWithRoles(long id);

    boolean existsByPhone_Phone(String phone);
}
