package com.training.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url="http://localhost:8083/fund-transfer", name = "fund-transfer-application")
public interface FundTransferInterface {

	@PostMapping("/{fromAccountId}/{toAccountId}/{amount}")
	public ResponseEntity<String> fundTransfer(@PathVariable("fromAccountId") long fromAccountId,
											   @PathVariable("toAccountId") long toAccountId,
											   @PathVariable("amount") double amount);
	
}
