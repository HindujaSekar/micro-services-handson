package com.training.parent.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartDto {
	
	private String[] productIds;
	private double total;

}
