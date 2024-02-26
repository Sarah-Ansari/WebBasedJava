package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@JsonProperty(access=Access.READ_ONLY)
	private Long id;
	@NotBlank(message="first name cannot be blank!!!")
	private String firstName;
	@NotBlank
	@Size(min=4,max=20)
	private String lastName;
	@Email
	private String email;
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "invalid password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotNull(message="role must be supplied!!!")
	private UserRole role;

}
