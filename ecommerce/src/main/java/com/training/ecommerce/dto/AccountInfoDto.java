package com.training.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountInfoDto {
	private String userName;
	private String email;
	private GenderType genderType; 
	
	public AccountInfoDto() {}

	public AccountInfoDto(String userName, String email, GenderType genderType) {
		super();
		this.userName = userName;
		this.email = email;
		this.genderType = genderType;
	}
	
}
