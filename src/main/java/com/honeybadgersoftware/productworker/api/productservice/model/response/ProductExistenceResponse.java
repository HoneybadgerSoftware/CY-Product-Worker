package com.honeybadgersoftware.productworker.api.productservice.model.response;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
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
