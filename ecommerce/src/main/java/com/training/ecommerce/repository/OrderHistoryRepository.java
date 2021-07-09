package com.training.ecommerce.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.ecommerce.entity.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{
	
	List<OrderHistory> findByOrderedDateBetween(LocalDate fromDate, LocalDate toDate);

}
