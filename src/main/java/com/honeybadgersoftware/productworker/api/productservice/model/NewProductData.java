package com.honeybadgersoftware.productworker.api.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class NewProductData {

    private Long id;
    private BigDecimal averagePrice;
    private String description;
    private String url;
}
