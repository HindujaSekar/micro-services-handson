package com.training.ecommerce.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class FundTransferDto {
	
	private long fromAccountId;
	private long toAccountId;
	private double amount;

}
