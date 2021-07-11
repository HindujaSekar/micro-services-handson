package com.training.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {
    private String brand;
    private String modelName;
    private double price;
}
