package com.training.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.ecommerce.entity.Product;
import com.training.ecommerce.entity.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByProductType(ProductType productType);
	
}
