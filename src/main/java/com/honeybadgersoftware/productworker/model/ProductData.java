package com.honeybadgersoftware.productworker.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductData {

    private Long id;
    private String name;
    private String manufacturer;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
