package com.honeybadgersoftware.productworker.api.productservice.model;

import com.honeybadgersoftware.productworker.model.SimplifiedProductData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CheckProductsExistenceRequest {
    private List<SimplifiedProductData> data;
}
