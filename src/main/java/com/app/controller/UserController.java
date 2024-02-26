package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	//dep: UserService
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody @Valid UserDto userDto){
		System.out.println("in user signup"+userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signupUser(userDto));
	}

}
