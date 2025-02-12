package com.pintoss.gitftmall.domain.membership.infra.repository;

import com.pintoss.gitftmall.domain.membership.domain.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail_Email(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail_Email(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :userId")
    Optional<User> findByIdWithRoles(@Param("userId") long userId);

    boolean existsByPhone_Phone(String phone);

    Page<User> findAll(Pageable pageable);
}
