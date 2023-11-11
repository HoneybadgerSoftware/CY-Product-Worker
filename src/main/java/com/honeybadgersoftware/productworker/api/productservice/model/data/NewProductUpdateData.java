package com.honeybadgersoftware.productworker.api.productservice.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class NewProductUpdateData {

    private Long id;
    private BigDecimal averagePrice;
    private String description;
    private String url;
}
