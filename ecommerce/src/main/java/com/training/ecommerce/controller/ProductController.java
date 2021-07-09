package com.training.ecommerce.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ecommerce.dto.CartDto;
import com.training.ecommerce.entity.OrderHistory;
import com.training.ecommerce.entity.Product;
import com.training.ecommerce.service.ProductService;

@RestController
@RequestMapping("e-commerce")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts(){
		return new ResponseEntity<>(service.findAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/{productType}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable("productType")String productType ){
		return new ResponseEntity<>(service.findProductsByCategory(productType),HttpStatus.OK);
	}
	@GetMapping("/history")
	public ResponseEntity<List<OrderHistory>> findMyOrders(){
		return new ResponseEntity<>(service.findMyOrders(),HttpStatus.OK);
	}
	@GetMapping("/{fromDate}/{toDate}")
	public ResponseEntity<List<OrderHistory>> findOrdersBetweenGivenDates(@PathVariable("fromDate") LocalDate fromDate,
			@PathVariable("toDate") LocalDate toDate){
		return new ResponseEntity<>(service.findOrdersBetweenGivenDates(fromDate, toDate),HttpStatus.OK);
	}
	@PostMapping("/{productIds}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productIds")String[] productIds){
		return new ResponseEntity<>(service.addProductToCart(productIds),HttpStatus.CREATED);
	}
	@PostMapping("/{cartId}/{productIds}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productIds") String[] productIds){
		return new ResponseEntity<>(service.updateCart(cartId, productIds),HttpStatus.OK);
	}
	

}
