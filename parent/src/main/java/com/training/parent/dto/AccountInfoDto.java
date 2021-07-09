package com.training.parent.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountInfoDto {
	private String userName;
	private String email;
	private double balance;
	public AccountInfoDto() {}
	public AccountInfoDto(String userName, String email, double balance) {
		super();
		this.userName = userName;
		this.email = email;
		this.balance = balance;
	}
	

}
