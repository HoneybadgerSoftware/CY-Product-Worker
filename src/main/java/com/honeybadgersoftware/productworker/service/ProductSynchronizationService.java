package com.honeybadgersoftware.productworker.service;

import com.honeybadgersoftware.productworker.model.ProductData;

import java.util.List;

public interface ProductSynchronizationService {

    void synchronizeProducts(List<ProductData> product);
}
