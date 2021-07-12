package com.training.ecommerce.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ecommerce.client.FundTransferInterface;
import com.training.ecommerce.dto.CartDto;
import com.training.ecommerce.dto.OrderDetailsDto;
import com.training.ecommerce.dto.ProductDto;
import com.training.ecommerce.entity.Cart;
import com.training.ecommerce.entity.OrderHistory;
import com.training.ecommerce.entity.OrderStatus;
import com.training.ecommerce.entity.PaymentStatus;
import com.training.ecommerce.entity.Product;
import com.training.ecommerce.entity.ProductType;
import com.training.ecommerce.exceptions.ProductExistsException;
import com.training.ecommerce.exceptions.ProductNotExistsException;
import com.training.ecommerce.exceptions.TransactionFailedException;
import com.training.ecommerce.exceptions.UnHandledCartException;
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
	@Autowired
	private FundTransferInterface fundTransferInterface;

	public List<Product> findAllProducts() {
		log.info("Got all products");
		return repository.findAll();
	}

	public List<Product> findProductsByCategory(ProductType productType) {
		log.info("Got all products based on type");
		return repository.findByProductType(productType);
	}

	public List<OrderHistory> findMyOrders() {
		log.info("Got all orders");
		return historyRepository.findAll();
	}

	public List<OrderHistory> findOrdersBetweenGivenDates(LocalDate fromDate, LocalDate toDate) {
		log.info("Got all orders between " + fromDate + " and " + toDate);
		return historyRepository.findByOrderedDateBetween(fromDate, toDate);
	}

	public CartDto addProductToCart(String productId) {
		if (cartRepository.findAll().isEmpty()) {
			List<Product> products = new ArrayList<>();
			products.add(repository.getById(Long.parseLong(productId)));
			double total = repository.getById(Long.parseLong(productId)).getPrice();
			Cart cart = Cart.builder().products(products).total(total).build();
			cartRepository.save(cart);
			log.info("added products to cart");
			return CartDto.builder().productId(Long.parseLong(productId)).total(total).build();
		} else {
			throw new UnHandledCartException("Please remove items from old cart or make payment to continue");
		}

	}

	public String updateCart(long cartId, long productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Product> products = cart.getProducts();
		double total = cart.getTotal();
		if (!products.contains(repository.getById(productId))) {
			products.add(repository.getById(productId));
			total += repository.getById(productId).getPrice();
			cart.setProducts(products);
			cart.setTotal(total);
			cartRepository.save(cart);
			log.info("added products to cart");
			return "UpdatedSucessfully";
		} else {
			throw new ProductExistsException("Product Already exists in cart");
		}
	}

	public String removeProductFromCart(long cartId, long productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Product> products = cart.getProducts();
		double total = cart.getTotal();
		if (products.contains(repository.getById(productId))) {
			products.remove(repository.getById(productId));
			total = total - repository.getById(productId).getPrice();
			cart.setProducts(products);
			cart.setTotal(total);
			cartRepository.save(cart);
			if(total == 0){
				cartRepository.delete(cart);
			}
			log.info("removed product from cart");
			return "RemovedSucessfully";
		} else {
			throw new ProductNotExistsException("Product not exists in cart");
		}
	}

	public OrderDetailsDto makePayment(long fromAccountId) {
		List<Cart> carts = cartRepository.findAll();
		Cart cart = carts.get(0);
		double amount = cart.getTotal();
		long toAccountId = 4;
		String message = fundTransferInterface.fundTransfer(fromAccountId, toAccountId, amount).getBody();
		List<Product> products = cart.getProducts();
		List<Product> productsForOrder = new ArrayList<>();
		for (Product product : products)
			productsForOrder.add(product);
		if (message.equals("transaction successful")) {
			OrderHistory history = OrderHistory.builder().products(productsForOrder).orderedDate(LocalDate.now())
					.orderedTime(LocalTime.now()).orderStatus(OrderStatus.OrderConfirmed)
					.paymentStatus(PaymentStatus.PaymentSuccess).total(cart.getTotal()).build();
			historyRepository.save(history);
			cartRepository.delete(cart);
			List<ProductDto> productDtos = new ArrayList<>();
			for (Product product : history.getProducts()) {
				productDtos.add(ProductDto.builder().brand(product.getBrand()).modelName(product.getModelName())
						.price(product.getPrice()).build());
			}
			return OrderDetailsDto.builder().products(productDtos).total(history.getTotal()).build();
		} else {
			throw new TransactionFailedException("transaction failed");
		}
	}
}
