package com.training.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.ecommerce.entity.ECommerceUser;

public interface UserRepository extends JpaRepository<ECommerceUser, Long>{
	
	ECommerceUser findByEmail(String email);
	boolean existsUserByEmail (String email);

}