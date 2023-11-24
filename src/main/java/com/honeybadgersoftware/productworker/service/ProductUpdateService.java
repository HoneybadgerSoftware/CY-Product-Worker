package com.honeybadgersoftware.productworker.service;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;

import java.util.List;

public interface ProductUpdateService {

    void sendProductsCreationRequest(List<ProductData> productData, List<ProductExistenceData> productExistenceData);
}
