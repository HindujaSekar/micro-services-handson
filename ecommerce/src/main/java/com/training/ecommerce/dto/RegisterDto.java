package com.training.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterDto {
	
	private String name;
	private String email;
	private String password;
	private GenderType gender;
	public RegisterDto() {}
	public RegisterDto(String name, String email, String password, GenderType gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	
	

}
