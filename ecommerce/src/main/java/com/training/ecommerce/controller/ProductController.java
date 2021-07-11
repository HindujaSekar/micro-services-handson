package com.training.ecommerce.controller;

import java.time.LocalDate;
import java.util.List;

import com.training.ecommerce.dto.*;
import com.training.ecommerce.entity.ProductType;
import com.training.ecommerce.service.FundTransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.ecommerce.entity.OrderHistory;
import com.training.ecommerce.entity.Product;
import com.training.ecommerce.service.ProductService;

@RestController
@RequestMapping("e-commerce")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@Autowired
	private FundTransferServiceImpl fundTransferService;

	@PostMapping("/{register}")
	public ResponseEntity<CredentialDto> register(@RequestBody RegisterDto registerDto){
		return new ResponseEntity<>(fundTransferService.register(registerDto), HttpStatus.CREATED);
	}
	@PostMapping("/payment/{fromAccountId}")
	public ResponseEntity<OrderDetailsDto> makePayment(@PathVariable("fromAccountId") long fromAccountId){
		return new ResponseEntity<>(service.makePayment(fromAccountId), HttpStatus.OK);
	}
	@PostMapping("/{login}")
	public ResponseEntity<AccountInfoDto> login(@RequestBody CredentialDto dto){
		return new ResponseEntity<>(fundTransferService.login(dto), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts(){
		return new ResponseEntity<>(service.findAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/{productType}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable("productType") ProductType productType ){
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
	@PostMapping("/cart/{productId}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productId")String productId){
		return new ResponseEntity<>(service.addProductToCart(productId),HttpStatus.CREATED);
	}
	@PutMapping("/{cartId}/{productId}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productId") long productId){
		return new ResponseEntity<>(service.updateCart(cartId, productId),HttpStatus.OK);
	}
	

}
