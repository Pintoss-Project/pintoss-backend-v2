package com.pintoss.gitftmall.infra.persistence.membership;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Long save(User user) {
        userJpaRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail_Email(email);
    }

    @Override
    public boolean existsByEmail_Email(String email) {
        return userJpaRepository.existsByEmail_Email(email);
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByIdWithRoles(long id) {
        return userJpaRepository.findByIdWithRoles(id);
    }

    @Override
    public boolean existsByPhone_Phone(String phone) {
        return userJpaRepository.existsByPhone_Phone(phone);
    }
}
