package com.app.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.app.dto.UserDto;
import com.app.entities.UserEntity;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired	private PasswordEncoder enc; 
	
	@Override
	public UserDto signupUser(@Valid UserDto userDto) {
		//dto-->entity
		UserEntity userEntity=mapper.map(userDto, UserEntity.class);
		userRepo.save(userEntity);
		UserDto dto = mapper.map(userEntity, UserDto.class);
		return dto;
	}

}
