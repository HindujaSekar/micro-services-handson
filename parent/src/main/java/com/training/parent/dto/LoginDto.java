package com.training.parent.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LoginDto {
	private String email;
	private String password;

}
