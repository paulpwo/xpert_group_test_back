package com.xpertgroup.demo.ports.in;

import java.util.Optional;
import com.xpertgroup.demo.dtos.LoginResponseDto;
import com.xpertgroup.demo.dtos.UserDto;

public interface UserUseCase {
    Optional<LoginResponseDto> login(String email, String password);
    UserDto register(UserDto userDto);
}