package com.training.parent.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.parent.dto.CartDto;
import com.training.parent.dto.Product;

@FeignClient(url="http://localhost:8085/e-commerce", name = "e-commerce-application")
public interface ECommerceInterface {
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts();
	@GetMapping("/{productType}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable("productType")String productType );
	@PostMapping("/{productIds}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productIds")String[] productIds);
	@PostMapping("/{cartId}/{productIds}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productIds") String[] productIds);
	
	

}
