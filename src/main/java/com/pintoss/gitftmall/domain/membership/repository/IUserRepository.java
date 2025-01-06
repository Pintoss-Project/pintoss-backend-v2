package com.pintoss.gitftmall.domain.membership.repository;

import com.pintoss.gitftmall.domain.membership.model.User;
import java.util.Optional;

public interface IUserRepository {

    Long save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
