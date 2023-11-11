package com.honeybadgersoftware.productworker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SynchronizeProductsRequest {

    private Long shopId;
    private List<ProductData> data;
}
