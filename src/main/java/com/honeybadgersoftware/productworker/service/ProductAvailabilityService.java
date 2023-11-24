package com.honeybadgersoftware.productworker.service;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;


public interface ProductAvailabilityService {

    void sendProductAvailabilityRequest(
            Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence,
            SynchronizeProductsRequest synchronizeProductsRequest);
}
