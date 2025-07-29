package com.xpertgroup.demo.adapters.out;

import org.springframework.stereotype.Repository;
import com.xpertgroup.demo.entities.User;
import com.xpertgroup.demo.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public java.util.Optional<User> findByEmail(String email) {
        log.debug("Finding user by email: {}", email);
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        log.debug("Saving user: {}", user.getEmail());
        return userJpaRepository.save(user);
    }
}