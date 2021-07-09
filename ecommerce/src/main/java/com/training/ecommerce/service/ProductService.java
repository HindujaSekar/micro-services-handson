package com.training.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ecommerce.dto.CartDto;
import com.training.ecommerce.entity.Cart;
import com.training.ecommerce.entity.OrderHistory;
import com.training.ecommerce.entity.Product;
import com.training.ecommerce.entity.ProductType;
import com.training.ecommerce.repository.CartRepository;
import com.training.ecommerce.repository.OrderHistoryRepository;
import com.training.ecommerce.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private OrderHistoryRepository historyRepository;
	@Autowired
	private CartRepository cartRepository;
	
	
	public List<Product> findAllProducts(){
		log.info("Got all products");
		return repository.findAll();
	}
	public List<Product> findProductsByCategory(String productType ){
		log.info("Got all products based on type");
		return repository.findByProductType(ProductType.valueOf(productType));
	}
	public List<OrderHistory> findMyOrders(){
		log.info("Got all orders");
		return historyRepository.findAll();
	}
	public List<OrderHistory> findOrdersBetweenGivenDates(LocalDate fromDate, LocalDate toDate){
		log.info("Got all orders between "+fromDate+ " and "+toDate);
		return historyRepository.findByOrderedDateBetween(fromDate, toDate);
	}
	public CartDto addProductToCart(String[] productIds){
		
		List<Product> products = new ArrayList<>();
		double total = 0;
		for(String id : productIds){
			products.add(repository.getById(Long.parseLong(id)));
			total+=repository.getById(Long.parseLong(id)).getPrice();
		}
		Cart cart = Cart.builder()
				.products(products)
				.total(total)
				.build();
		cartRepository.save(cart);
		log.info("added products to cart");
		return CartDto.builder()
				.productIds(productIds)
				.total(total).build();
						
	}
	public String updateCart(long cartId, String[] productIds){
		Cart cart = cartRepository.findById(cartId).get();
		List<Product> products = cart.getProducts();
		double total = cart.getTotal();
		for(String id : productIds){
			products.add(repository.getById(Long.parseLong(id)));
			total+=repository.getById(Long.parseLong(id)).getPrice();
		}
		cart.setProducts(products);
		cart.setTotal(total);
		cartRepository.save(cart);
		log.info("added products to cart");
		return"UpdatedSucessfully";	
	}
}
