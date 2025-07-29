package com.xpertgroup.demo.ports.out;

import java.util.Optional;
import com.xpertgroup.demo.entities.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}