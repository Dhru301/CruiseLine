package com.cruiseline.modules.auth.dto;

import com.cruiseline.common.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	@NotBlank @Size(max = 120)
	private String name;
	
	@NotBlank @Email
	private String email;
	
	@NotBlank @Size(min = 8, max = 100)
	private String password;
	
	@Size(max = 30)
	private String phone;
	
	private Role role;
	
	private String vesselId;
}
