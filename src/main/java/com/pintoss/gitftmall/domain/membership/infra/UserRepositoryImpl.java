package com.pintoss.gitftmall.domain.membership.infra;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userJpaRepository.findAll(pageable);
    }

}
