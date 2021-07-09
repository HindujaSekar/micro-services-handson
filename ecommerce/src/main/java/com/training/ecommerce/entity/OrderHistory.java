package com.training.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Builder
@Data
public class OrderHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	@ManyToMany
	@JoinColumn
	private List<Product> products;
	private LocalDate orderedDate;
	private LocalTime orderedTime;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;

}
