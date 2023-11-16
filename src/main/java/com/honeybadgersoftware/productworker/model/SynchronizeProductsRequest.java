package com.honeybadgersoftware.productworker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SynchronizeProductsRequest {

    private Long shopId;
    private List<ProductData> data;
}
