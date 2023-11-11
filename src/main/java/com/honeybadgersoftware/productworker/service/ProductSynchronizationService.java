package com.honeybadgersoftware.productworker.service;

import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;

public interface ProductSynchronizationService {

    void synchronizeProducts(SynchronizeProductsRequest products);
}
