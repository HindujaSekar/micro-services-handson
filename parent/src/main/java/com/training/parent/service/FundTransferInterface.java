package com.training.parent.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.parent.dto.AccountInfoDto;
import com.training.parent.dto.CredentialDto;
import com.training.parent.dto.FundTransferDto;
import com.training.parent.dto.LoginDto;
import com.training.parent.dto.RegisterDto;

@FeignClient(url="http://localhost:8083/fund-transfer", name = "fund-transfer-application")
public interface FundTransferInterface {
	
	@PostMapping("/{register}")
	public ResponseEntity<CredentialDto> register(RegisterDto registerDto);
	@PostMapping("/{fundtransfer}")
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransferDto fundTransferDto);
	@PostMapping("/{login}")
	public ResponseEntity<AccountInfoDto> login(@RequestBody LoginDto loginDto);

}
