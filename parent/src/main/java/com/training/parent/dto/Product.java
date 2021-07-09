package com.training.parent.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {
	
	private long productId;
	private ProductType productType;
	private String productSubCategory;
	private String brand;
	private String modelName;
	private String dealerInfo;
	private double customerReview;
	private double price;
	

}
