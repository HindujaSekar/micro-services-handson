package com.training.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CredentialDto {
	private String email;
	private String password;
	public CredentialDto() {}
	public CredentialDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	

}
