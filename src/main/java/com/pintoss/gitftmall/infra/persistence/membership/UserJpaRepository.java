package com.pintoss.gitftmall.infra.persistence.membership;

import com.pintoss.gitftmall.domain.membership.model.User;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);
}
