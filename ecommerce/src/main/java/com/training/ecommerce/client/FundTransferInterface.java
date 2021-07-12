package com.training.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.ecommerce.dto.AccountInfoDto;
import com.training.ecommerce.dto.AccountType;
import com.training.ecommerce.dto.CredentialDto;
import com.training.ecommerce.dto.GenderType;

@FeignClient(url="http://localhost:8083/fund-transfer", name = "fund-transfer-application")
public interface FundTransferInterface {

	@PostMapping("/{name}/{email}/{password}/{gender}/{accountType}/{balance}")
	public ResponseEntity<CredentialDto> register(@PathVariable("name") String name,
												  @PathVariable("email") String email,
												  @PathVariable("password") String password,
												  @PathVariable("gender") GenderType gender,
												  @PathVariable("accountType") AccountType accountType,
												  @PathVariable("balance") double balance);
	@PostMapping("/{fromAccountId}/{toAccountId}/{amount}")
	public ResponseEntity<String> fundTransfer(@PathVariable("fromAccountId") long fromAccountId,
											   @PathVariable("toAccountId") long toAccountId,
											   @PathVariable("amount") double amount);
	@PostMapping("/{email}/{password}")
	public ResponseEntity<AccountInfoDto> login(@PathVariable("email") String email, @PathVariable("password") String password);

}
