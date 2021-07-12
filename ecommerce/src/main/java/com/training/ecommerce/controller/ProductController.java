package com.training.ecommerce.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ecommerce.dto.AccountInfoDto;
import com.training.ecommerce.dto.CartDto;
import com.training.ecommerce.dto.CredentialDto;
import com.training.ecommerce.dto.OrderDetailsDto;
import com.training.ecommerce.dto.RegisterDto;
import com.training.ecommerce.entity.OrderHistory;
import com.training.ecommerce.entity.Product;
import com.training.ecommerce.entity.ProductType;
import com.training.ecommerce.service.ProductService;

@RestController
@RequestMapping("e-commerce")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/{register}")
	public ResponseEntity<CredentialDto> register(@RequestBody RegisterDto registerDto){
		return new ResponseEntity<>(service.addUser(registerDto), HttpStatus.CREATED);
	}
	@PostMapping("/payment/{fromAccountId}")
	public ResponseEntity<OrderDetailsDto> makePayment(@PathVariable("fromAccountId") long fromAccountId){
		return new ResponseEntity<>(service.makePayment(fromAccountId), HttpStatus.OK);
	}
	@PostMapping("/{login}")
	public ResponseEntity<AccountInfoDto> login(@RequestBody CredentialDto dto){
		return new ResponseEntity<>(service.login(dto), HttpStatus.OK);
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
	public ResponseEntity<List<OrderHistory>> findOrdersBetweenGivenDates(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate){
		return new ResponseEntity<>(service.findOrdersBetweenGivenDates(LocalDate.parse(fromDate),
				LocalDate.parse(toDate)),HttpStatus.OK);
	}
	@PostMapping("/cart/{productId}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productId")String productId){
		return new ResponseEntity<>(service.addProductToCart(productId),HttpStatus.CREATED);
	}
	@DeleteMapping("{cartId}/{productId}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable("cartId") long cartId,
			@PathVariable("productId") long productId){
		return new ResponseEntity<>(service.removeProductFromCart(cartId, productId),HttpStatus.OK);
	}
	@PutMapping("/{cartId}/{productId}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productId") long productId){
		return new ResponseEntity<>(service.updateCart(cartId, productId),HttpStatus.OK);
	}
	

}
