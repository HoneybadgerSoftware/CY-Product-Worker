package com.honeybadgersoftware.productworker.api.productservice.model;

import com.honeybadgersoftware.productworker.model.ProductExistenceData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductExistenceResponse {

    private List<ProductExistenceData> productExistenceData;
}
