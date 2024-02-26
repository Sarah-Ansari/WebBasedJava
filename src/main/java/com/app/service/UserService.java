package com.app.service;

import javax.validation.Valid;

import com.app.dto.UserDto;

public interface UserService {

	UserDto signupUser(@Valid UserDto userDto);

}
