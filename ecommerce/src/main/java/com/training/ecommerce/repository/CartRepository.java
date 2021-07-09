package com.training.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	
}
