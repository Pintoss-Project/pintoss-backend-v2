package com.pintoss.gitftmall.infra.persistence.membership;

import com.pintoss.gitftmall.domain.membership.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail_Email(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail_Email(String email);
}
