package com.training.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private double total;
    public OrderHistory(){}

    public OrderHistory(long orderId, List<Product> products, LocalDate orderedDate,
                        LocalTime orderedTime, OrderStatus orderStatus,
                        PaymentStatus paymentStatus, double total) {
        this.orderId = orderId;
        this.products = products;
        this.orderedDate = orderedDate;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.total = total;
    }
}
