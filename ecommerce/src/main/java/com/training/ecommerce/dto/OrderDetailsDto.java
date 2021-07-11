package com.training.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderDetailsDto {
    private List<ProductDto> products;
    private double total;
}

