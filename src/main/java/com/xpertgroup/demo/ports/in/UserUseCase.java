package com.xpertgroup.demo.ports.in;

import java.util.Optional;
import com.xpertgroup.demo.dtos.UserDto;

public interface UserUseCase {
    Optional<UserDto> login(String email, String password);
    UserDto register(UserDto userDto);
}