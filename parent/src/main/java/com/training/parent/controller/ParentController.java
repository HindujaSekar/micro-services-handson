package com.training.parent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.parent.dto.AccountInfoDto;
import com.training.parent.dto.CartDto;
import com.training.parent.dto.CredentialDto;
import com.training.parent.dto.FundTransferDto;
import com.training.parent.dto.LoginDto;
import com.training.parent.dto.Product;
import com.training.parent.dto.RegisterDto;
import com.training.parent.service.ECommerceInterface;
import com.training.parent.service.FundTransferInterface;

@RestController
@RequestMapping("parent")
public class ParentController {
	@Autowired
	private ECommerceInterface commerceInterface;
	@Autowired
	private FundTransferInterface fundTransferInterface;
	
	@PostMapping("/{register}")
	public ResponseEntity<CredentialDto> register(RegisterDto registerDto){
		return fundTransferInterface.register(registerDto);
	}
	@PostMapping("/{fundtransfer}")
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransferDto fundTransferDto){
		return fundTransferInterface.fundTransfer(fundTransferDto);
	}
	@PostMapping("/{login}")
	public ResponseEntity<AccountInfoDto> login(LoginDto loginDto){
		return fundTransferInterface.login(loginDto);
	}
	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts(){
		return commerceInterface.findAllProducts();
	}
	@GetMapping("/{productType}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable("productType")String productType ){
		return commerceInterface.findProductsByCategory(productType);
	}
	@PostMapping("/{productIds}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productIds")String[] productIds){
		return commerceInterface.addProductToCart(productIds);
	}
	/*@PostMapping("/{cartId}/{productIds}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productIds") String[] productIds){
		return commerceInterface.updateCart(cartId, productIds);
	}*/

}
